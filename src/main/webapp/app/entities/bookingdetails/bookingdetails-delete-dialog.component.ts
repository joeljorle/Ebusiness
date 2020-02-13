import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBookingdetails } from 'app/shared/model/bookingdetails.model';
import { BookingdetailsService } from './bookingdetails.service';

@Component({
  templateUrl: './bookingdetails-delete-dialog.component.html'
})
export class BookingdetailsDeleteDialogComponent {
  bookingdetails?: IBookingdetails;

  constructor(
    protected bookingdetailsService: BookingdetailsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.bookingdetailsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('bookingdetailsListModification');
      this.activeModal.close();
    });
  }
}
