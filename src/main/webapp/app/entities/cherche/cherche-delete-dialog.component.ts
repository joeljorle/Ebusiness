import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICherche } from 'app/shared/model/cherche.model';
import { ChercheService } from './cherche.service';

@Component({
  templateUrl: './cherche-delete-dialog.component.html'
})
export class ChercheDeleteDialogComponent {
  cherche?: ICherche;

  constructor(protected chercheService: ChercheService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.chercheService.delete(id).subscribe(() => {
      this.eventManager.broadcast('chercheListModification');
      this.activeModal.close();
    });
  }
}
