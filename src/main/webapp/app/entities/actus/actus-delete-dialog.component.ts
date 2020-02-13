import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IActus } from 'app/shared/model/actus.model';
import { ActusService } from './actus.service';

@Component({
  templateUrl: './actus-delete-dialog.component.html'
})
export class ActusDeleteDialogComponent {
  actus?: IActus;

  constructor(protected actusService: ActusService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.actusService.delete(id).subscribe(() => {
      this.eventManager.broadcast('actusListModification');
      this.activeModal.close();
    });
  }
}
