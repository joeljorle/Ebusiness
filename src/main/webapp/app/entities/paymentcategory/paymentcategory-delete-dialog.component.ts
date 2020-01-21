import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPaymentcategory } from 'app/shared/model/paymentcategory.model';
import { PaymentcategoryService } from './paymentcategory.service';

@Component({
  templateUrl: './paymentcategory-delete-dialog.component.html'
})
export class PaymentcategoryDeleteDialogComponent {
  paymentcategory?: IPaymentcategory;

  constructor(
    protected paymentcategoryService: PaymentcategoryService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.paymentcategoryService.delete(id).subscribe(() => {
      this.eventManager.broadcast('paymentcategoryListModification');
      this.activeModal.close();
    });
  }
}
