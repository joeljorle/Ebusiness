import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBookingproducts, Bookingproducts } from 'app/shared/model/bookingproducts.model';
import { BookingproductsService } from './bookingproducts.service';
import { IBooking } from 'app/shared/model/booking.model';
import { BookingService } from 'app/entities/booking/booking.service';

@Component({
  selector: 'jhi-bookingproducts-update',
  templateUrl: './bookingproducts-update.component.html'
})
export class BookingproductsUpdateComponent implements OnInit {
  isSaving = false;
  bookings: IBooking[] = [];

  editForm = this.fb.group({
    id: [],
    bookingid: [null, [Validators.required]],
    productid: [null, [Validators.required]],
    bookingqty: [null, [Validators.min(0)]],
    bookingId: []
  });

  constructor(
    protected bookingproductsService: BookingproductsService,
    protected bookingService: BookingService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bookingproducts }) => {
      this.updateForm(bookingproducts);

      this.bookingService.query().subscribe((res: HttpResponse<IBooking[]>) => (this.bookings = res.body || []));
    });
  }

  updateForm(bookingproducts: IBookingproducts): void {
    this.editForm.patchValue({
      id: bookingproducts.id,
      bookingid: bookingproducts.bookingid,
      productid: bookingproducts.productid,
      bookingqty: bookingproducts.bookingqty,
      bookingId: bookingproducts.bookingId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bookingproducts = this.createFromForm();
    if (bookingproducts.id !== undefined) {
      this.subscribeToSaveResponse(this.bookingproductsService.update(bookingproducts));
    } else {
      this.subscribeToSaveResponse(this.bookingproductsService.create(bookingproducts));
    }
  }

  private createFromForm(): IBookingproducts {
    return {
      ...new Bookingproducts(),
      id: this.editForm.get(['id'])!.value,
      bookingid: this.editForm.get(['bookingid'])!.value,
      productid: this.editForm.get(['productid'])!.value,
      bookingqty: this.editForm.get(['bookingqty'])!.value,
      bookingId: this.editForm.get(['bookingId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBookingproducts>>): void {
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

  trackById(index: number, item: IBooking): any {
    return item.id;
  }
}
