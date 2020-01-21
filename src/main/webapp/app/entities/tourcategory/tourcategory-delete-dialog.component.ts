import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITourcategory } from 'app/shared/model/tourcategory.model';
import { TourcategoryService } from './tourcategory.service';

@Component({
  templateUrl: './tourcategory-delete-dialog.component.html'
})
export class TourcategoryDeleteDialogComponent {
  tourcategory?: ITourcategory;

  constructor(
    protected tourcategoryService: TourcategoryService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tourcategoryService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tourcategoryListModification');
      this.activeModal.close();
    });
  }
}
