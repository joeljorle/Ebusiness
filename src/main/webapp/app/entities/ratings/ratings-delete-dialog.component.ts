import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRatings } from 'app/shared/model/ratings.model';
import { RatingsService } from './ratings.service';

@Component({
  templateUrl: './ratings-delete-dialog.component.html'
})
export class RatingsDeleteDialogComponent {
  ratings?: IRatings;

  constructor(protected ratingsService: RatingsService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ratingsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ratingsListModification');
      this.activeModal.close();
    });
  }
}
