import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICategorydetails } from 'app/shared/model/categorydetails.model';
import { CategorydetailsService } from './categorydetails.service';

@Component({
  templateUrl: './categorydetails-delete-dialog.component.html'
})
export class CategorydetailsDeleteDialogComponent {
  categorydetails?: ICategorydetails;

  constructor(
    protected categorydetailsService: CategorydetailsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.categorydetailsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('categorydetailsListModification');
      this.activeModal.close();
    });
  }
}
