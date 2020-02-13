import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IBooking, Booking } from 'app/shared/model/booking.model';
import { BookingService } from './booking.service';
import { ICurrency } from 'app/shared/model/currency.model';
import { CurrencyService } from 'app/entities/currency/currency.service';
import { IBookingview } from 'app/shared/model/bookingview.model';
import { BookingviewService } from 'app/entities/bookingview/bookingview.service';
import { IBookingdetails } from 'app/shared/model/bookingdetails.model';
import { BookingdetailsService } from 'app/entities/bookingdetails/bookingdetails.service';
import { ICategory } from 'app/shared/model/category.model';
import { CategoryService } from 'app/entities/category/category.service';
import { IEvenement } from 'app/shared/model/evenement.model';
import { EvenementService } from 'app/entities/evenement/evenement.service';

type SelectableEntity = ICurrency | IBookingview | IBookingdetails | ICategory | IEvenement;

@Component({
  selector: 'jhi-booking-update',
  templateUrl: './booking-update.component.html'
})
export class BookingUpdateComponent implements OnInit {
  isSaving = false;
  currencies: ICurrency[] = [];
  bookingviews: IBookingview[] = [];
  bookingdetails: IBookingdetails[] = [];
  categories: ICategory[] = [];
  evenements: IEvenement[] = [];

  editForm = this.fb.group({
    id: [],
    slug: [null, [Validators.required]],
    userid: [],
    tourid: [],
    tourgroupid: [],
    categoryid: [],
    productid: [],
    evenementid: [],
    createdat: [],
    updatedat: [],
    currencyId: [],
    bookingviewId: [],
    bookingdetailsId: [],
    categoryId: [],
    evenementId: []
  });

  constructor(
    protected bookingService: BookingService,
    protected currencyService: CurrencyService,
    protected bookingviewService: BookingviewService,
    protected bookingdetailsService: BookingdetailsService,
    protected categoryService: CategoryService,
    protected evenementService: EvenementService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ booking }) => {
      if (!booking.id) {
        const today = moment().startOf('day');
        booking.createdat = today;
        booking.updatedat = today;
      }

      this.updateForm(booking);

      this.currencyService
        .query({ filter: 'booking-is-null' })
        .pipe(
          map((res: HttpResponse<ICurrency[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ICurrency[]) => {
          if (!booking.currencyId) {
            this.currencies = resBody;
          } else {
            this.currencyService
              .find(booking.currencyId)
              .pipe(
                map((subRes: HttpResponse<ICurrency>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ICurrency[]) => (this.currencies = concatRes));
          }
        });

      this.bookingviewService
        .query({ filter: 'booking-is-null' })
        .pipe(
          map((res: HttpResponse<IBookingview[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IBookingview[]) => {
          if (!booking.bookingviewId) {
            this.bookingviews = resBody;
          } else {
            this.bookingviewService
              .find(booking.bookingviewId)
              .pipe(
                map((subRes: HttpResponse<IBookingview>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IBookingview[]) => (this.bookingviews = concatRes));
          }
        });

      this.bookingdetailsService
        .query({ filter: 'booking-is-null' })
        .pipe(
          map((res: HttpResponse<IBookingdetails[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IBookingdetails[]) => {
          if (!booking.bookingdetailsId) {
            this.bookingdetails = resBody;
          } else {
            this.bookingdetailsService
              .find(booking.bookingdetailsId)
              .pipe(
                map((subRes: HttpResponse<IBookingdetails>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IBookingdetails[]) => (this.bookingdetails = concatRes));
          }
        });

      this.categoryService.query().subscribe((res: HttpResponse<ICategory[]>) => (this.categories = res.body || []));

      this.evenementService.query().subscribe((res: HttpResponse<IEvenement[]>) => (this.evenements = res.body || []));
    });
  }

  updateForm(booking: IBooking): void {
    this.editForm.patchValue({
      id: booking.id,
      slug: booking.slug,
      userid: booking.userid,
      tourid: booking.tourid,
      tourgroupid: booking.tourgroupid,
      categoryid: booking.categoryid,
      productid: booking.productid,
      evenementid: booking.evenementid,
      createdat: booking.createdat ? booking.createdat.format(DATE_TIME_FORMAT) : null,
      updatedat: booking.updatedat ? booking.updatedat.format(DATE_TIME_FORMAT) : null,
      currencyId: booking.currencyId,
      bookingviewId: booking.bookingviewId,
      bookingdetailsId: booking.bookingdetailsId,
      categoryId: booking.categoryId,
      evenementId: booking.evenementId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const booking = this.createFromForm();
    if (booking.id !== undefined) {
      this.subscribeToSaveResponse(this.bookingService.update(booking));
    } else {
      this.subscribeToSaveResponse(this.bookingService.create(booking));
    }
  }

  private createFromForm(): IBooking {
    return {
      ...new Booking(),
      id: this.editForm.get(['id'])!.value,
      slug: this.editForm.get(['slug'])!.value,
      userid: this.editForm.get(['userid'])!.value,
      tourid: this.editForm.get(['tourid'])!.value,
      tourgroupid: this.editForm.get(['tourgroupid'])!.value,
      categoryid: this.editForm.get(['categoryid'])!.value,
      productid: this.editForm.get(['productid'])!.value,
      evenementid: this.editForm.get(['evenementid'])!.value,
      createdat: this.editForm.get(['createdat'])!.value ? moment(this.editForm.get(['createdat'])!.value, DATE_TIME_FORMAT) : undefined,
      updatedat: this.editForm.get(['updatedat'])!.value ? moment(this.editForm.get(['updatedat'])!.value, DATE_TIME_FORMAT) : undefined,
      currencyId: this.editForm.get(['currencyId'])!.value,
      bookingviewId: this.editForm.get(['bookingviewId'])!.value,
      bookingdetailsId: this.editForm.get(['bookingdetailsId'])!.value,
      categoryId: this.editForm.get(['categoryId'])!.value,
      evenementId: this.editForm.get(['evenementId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBooking>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
