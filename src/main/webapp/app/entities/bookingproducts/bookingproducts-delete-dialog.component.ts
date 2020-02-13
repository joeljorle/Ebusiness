import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBookingproducts } from 'app/shared/model/bookingproducts.model';
import { BookingproductsService } from './bookingproducts.service';

@Component({
  templateUrl: './bookingproducts-delete-dialog.component.html'
})
export class BookingproductsDeleteDialogComponent {
  bookingproducts?: IBookingproducts;

  constructor(
    protected bookingproductsService: BookingproductsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.bookingproductsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('bookingproductsListModification');
      this.activeModal.close();
    });
  }
}
