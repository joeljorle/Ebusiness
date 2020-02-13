import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPaymentaction } from 'app/shared/model/paymentaction.model';
import { PaymentactionService } from './paymentaction.service';

@Component({
  templateUrl: './paymentaction-delete-dialog.component.html'
})
export class PaymentactionDeleteDialogComponent {
  paymentaction?: IPaymentaction;

  constructor(
    protected paymentactionService: PaymentactionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.paymentactionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('paymentactionListModification');
      this.activeModal.close();
    });
  }
}
