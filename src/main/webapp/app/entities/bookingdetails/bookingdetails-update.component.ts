import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IBookingdetails, Bookingdetails } from 'app/shared/model/bookingdetails.model';
import { BookingdetailsService } from './bookingdetails.service';
import { AlertError } from 'app/shared/alert/alert-error.model';

@Component({
  selector: 'jhi-bookingdetails-update',
  templateUrl: './bookingdetails-update.component.html'
})
export class BookingdetailsUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    subtotal: [],
    total: [],
    tax: [],
    shipping: [],
    shippingstate: [],
    about: [],
    couponconde: [],
    qrcode: [],
    code: [],
    url: [],
    token: [],
    paymentmode: [],
    startdate: [],
    enddate: [],
    enventstate: [],
    places: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected bookingdetailsService: BookingdetailsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bookingdetails }) => {
      if (!bookingdetails.id) {
        const today = moment().startOf('day');
        bookingdetails.startdate = today;
        bookingdetails.enddate = today;
      }

      this.updateForm(bookingdetails);
    });
  }

  updateForm(bookingdetails: IBookingdetails): void {
    this.editForm.patchValue({
      id: bookingdetails.id,
      subtotal: bookingdetails.subtotal,
      total: bookingdetails.total,
      tax: bookingdetails.tax,
      shipping: bookingdetails.shipping,
      shippingstate: bookingdetails.shippingstate,
      about: bookingdetails.about,
      couponconde: bookingdetails.couponconde,
      qrcode: bookingdetails.qrcode,
      code: bookingdetails.code,
      url: bookingdetails.url,
      token: bookingdetails.token,
      paymentmode: bookingdetails.paymentmode,
      startdate: bookingdetails.startdate ? bookingdetails.startdate.format(DATE_TIME_FORMAT) : null,
      enddate: bookingdetails.enddate ? bookingdetails.enddate.format(DATE_TIME_FORMAT) : null,
      enventstate: bookingdetails.enventstate,
      places: bookingdetails.places
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
    const bookingdetails = this.createFromForm();
    if (bookingdetails.id !== undefined) {
      this.subscribeToSaveResponse(this.bookingdetailsService.update(bookingdetails));
    } else {
      this.subscribeToSaveResponse(this.bookingdetailsService.create(bookingdetails));
    }
  }

  private createFromForm(): IBookingdetails {
    return {
      ...new Bookingdetails(),
      id: this.editForm.get(['id'])!.value,
      subtotal: this.editForm.get(['subtotal'])!.value,
      total: this.editForm.get(['total'])!.value,
      tax: this.editForm.get(['tax'])!.value,
      shipping: this.editForm.get(['shipping'])!.value,
      shippingstate: this.editForm.get(['shippingstate'])!.value,
      about: this.editForm.get(['about'])!.value,
      couponconde: this.editForm.get(['couponconde'])!.value,
      qrcode: this.editForm.get(['qrcode'])!.value,
      code: this.editForm.get(['code'])!.value,
      url: this.editForm.get(['url'])!.value,
      token: this.editForm.get(['token'])!.value,
      paymentmode: this.editForm.get(['paymentmode'])!.value,
      startdate: this.editForm.get(['startdate'])!.value ? moment(this.editForm.get(['startdate'])!.value, DATE_TIME_FORMAT) : undefined,
      enddate: this.editForm.get(['enddate'])!.value ? moment(this.editForm.get(['enddate'])!.value, DATE_TIME_FORMAT) : undefined,
      enventstate: this.editForm.get(['enventstate'])!.value,
      places: this.editForm.get(['places'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBookingdetails>>): void {
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
