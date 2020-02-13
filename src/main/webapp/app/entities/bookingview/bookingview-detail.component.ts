import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IBookingview } from 'app/shared/model/bookingview.model';

@Component({
  selector: 'jhi-bookingview-detail',
  templateUrl: './bookingview-detail.component.html'
})
export class BookingviewDetailComponent implements OnInit {
  bookingview: IBookingview | null = null;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bookingview }) => (this.bookingview = bookingview));
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  previousState(): void {
    window.history.back();
  }
}
