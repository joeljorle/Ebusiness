import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITourgroup } from 'app/shared/model/tourgroup.model';
import { TourgroupService } from './tourgroup.service';

@Component({
  templateUrl: './tourgroup-delete-dialog.component.html'
})
export class TourgroupDeleteDialogComponent {
  tourgroup?: ITourgroup;

  constructor(protected tourgroupService: TourgroupService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tourgroupService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tourgroupListModification');
      this.activeModal.close();
    });
  }
}
