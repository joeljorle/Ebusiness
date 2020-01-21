import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IBookingdetails } from 'app/shared/model/bookingdetails.model';

@Component({
  selector: 'jhi-bookingdetails-detail',
  templateUrl: './bookingdetails-detail.component.html'
})
export class BookingdetailsDetailComponent implements OnInit {
  bookingdetails: IBookingdetails | null = null;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bookingdetails }) => {
      this.bookingdetails = bookingdetails;
    });
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
