import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPaymentmethod } from 'app/shared/model/paymentmethod.model';
import { PaymentmethodService } from './paymentmethod.service';

@Component({
  templateUrl: './paymentmethod-delete-dialog.component.html'
})
export class PaymentmethodDeleteDialogComponent {
  paymentmethod?: IPaymentmethod;

  constructor(
    protected paymentmethodService: PaymentmethodService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.paymentmethodService.delete(id).subscribe(() => {
      this.eventManager.broadcast('paymentmethodListModification');
      this.activeModal.close();
    });
  }
}
