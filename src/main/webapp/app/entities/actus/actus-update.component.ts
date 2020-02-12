import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IActus, Actus } from 'app/shared/model/actus.model';
import { ActusService } from './actus.service';
import { AlertError } from 'app/shared/alert/alert-error.model';

@Component({
  selector: 'jhi-actus-update',
  templateUrl: './actus-update.component.html'
})
export class ActusUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    slug: [null, [Validators.required]],
    name: [],
    abrev: [],
    userid: [],
    image: [],
    imageContentType: [],
    details: [],
    createdat: [],
    updatedat: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected actusService: ActusService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ actus }) => {
      if (!actus.id) {
        const today = moment().startOf('day');
        actus.createdat = today;
        actus.updatedat = today;
      }

      this.updateForm(actus);
    });
  }

  updateForm(actus: IActus): void {
    this.editForm.patchValue({
      id: actus.id,
      slug: actus.slug,
      name: actus.name,
      abrev: actus.abrev,
      userid: actus.userid,
      image: actus.image,
      imageContentType: actus.imageContentType,
      details: actus.details,
      createdat: actus.createdat ? actus.createdat.format(DATE_TIME_FORMAT) : null,
      updatedat: actus.updatedat ? actus.updatedat.format(DATE_TIME_FORMAT) : null
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
        new JhiEventWithContent<AlertError>('eBusinessApp.error', { message: err.message })
      );
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const actus = this.createFromForm();
    if (actus.id !== undefined) {
      this.subscribeToSaveResponse(this.actusService.update(actus));
    } else {
      this.subscribeToSaveResponse(this.actusService.create(actus));
    }
  }

  private createFromForm(): IActus {
    return {
      ...new Actus(),
      id: this.editForm.get(['id'])!.value,
      slug: this.editForm.get(['slug'])!.value,
      name: this.editForm.get(['name'])!.value,
      abrev: this.editForm.get(['abrev'])!.value,
      userid: this.editForm.get(['userid'])!.value,
      imageContentType: this.editForm.get(['imageContentType'])!.value,
      image: this.editForm.get(['image'])!.value,
      details: this.editForm.get(['details'])!.value,
      createdat: this.editForm.get(['createdat'])!.value ? moment(this.editForm.get(['createdat'])!.value, DATE_TIME_FORMAT) : undefined,
      updatedat: this.editForm.get(['updatedat'])!.value ? moment(this.editForm.get(['updatedat'])!.value, DATE_TIME_FORMAT) : undefined
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IActus>>): void {
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
