import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IBookingview, Bookingview } from 'app/shared/model/bookingview.model';
import { BookingviewService } from './bookingview.service';
import { AlertError } from 'app/shared/alert/alert-error.model';

@Component({
  selector: 'jhi-bookingview-update',
  templateUrl: './bookingview-update.component.html'
})
export class BookingviewUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    title: [],
    subtitle: [],
    logo: [],
    logodata: [],
    logodataContentType: [],
    bg: [],
    bgdata: [],
    bgdataContentType: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected bookingviewService: BookingviewService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bookingview }) => {
      this.updateForm(bookingview);
    });
  }

  updateForm(bookingview: IBookingview): void {
    this.editForm.patchValue({
      id: bookingview.id,
      title: bookingview.title,
      subtitle: bookingview.subtitle,
      logo: bookingview.logo,
      logodata: bookingview.logodata,
      logodataContentType: bookingview.logodataContentType,
      bg: bookingview.bg,
      bgdata: bookingview.bgdata,
      bgdataContentType: bookingview.bgdataContentType
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('ebusinessApp.error', { ...err, key: 'error.file.' + err.key })
      );
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bookingview = this.createFromForm();
    if (bookingview.id !== undefined) {
      this.subscribeToSaveResponse(this.bookingviewService.update(bookingview));
    } else {
      this.subscribeToSaveResponse(this.bookingviewService.create(bookingview));
    }
  }

  private createFromForm(): IBookingview {
    return {
      ...new Bookingview(),
      id: this.editForm.get(['id'])!.value,
      title: this.editForm.get(['title'])!.value,
      subtitle: this.editForm.get(['subtitle'])!.value,
      logo: this.editForm.get(['logo'])!.value,
      logodataContentType: this.editForm.get(['logodataContentType'])!.value,
      logodata: this.editForm.get(['logodata'])!.value,
      bg: this.editForm.get(['bg'])!.value,
      bgdataContentType: this.editForm.get(['bgdataContentType'])!.value,
      bgdata: this.editForm.get(['bgdata'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBookingview>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
