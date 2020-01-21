import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBookingview } from 'app/shared/model/bookingview.model';
import { BookingviewService } from './bookingview.service';

@Component({
  templateUrl: './bookingview-delete-dialog.component.html'
})
export class BookingviewDeleteDialogComponent {
  bookingview?: IBookingview;

  constructor(
    protected bookingviewService: BookingviewService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.bookingviewService.delete(id).subscribe(() => {
      this.eventManager.broadcast('bookingviewListModification');
      this.activeModal.close();
    });
  }
}
