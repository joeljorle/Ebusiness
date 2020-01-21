import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICartproducts } from 'app/shared/model/cartproducts.model';
import { CartproductsService } from './cartproducts.service';

@Component({
  templateUrl: './cartproducts-delete-dialog.component.html'
})
export class CartproductsDeleteDialogComponent {
  cartproducts?: ICartproducts;

  constructor(
    protected cartproductsService: CartproductsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.cartproductsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('cartproductsListModification');
      this.activeModal.close();
    });
  }
}
