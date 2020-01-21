import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBookingproducts } from 'app/shared/model/bookingproducts.model';

@Component({
  selector: 'jhi-bookingproducts-detail',
  templateUrl: './bookingproducts-detail.component.html'
})
export class BookingproductsDetailComponent implements OnInit {
  bookingproducts: IBookingproducts | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bookingproducts }) => {
      this.bookingproducts = bookingproducts;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
