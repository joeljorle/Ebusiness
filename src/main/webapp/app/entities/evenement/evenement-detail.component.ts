import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IEvenement } from 'app/shared/model/evenement.model';

@Component({
  selector: 'jhi-evenement-detail',
  templateUrl: './evenement-detail.component.html'
})
export class EvenementDetailComponent implements OnInit {
  evenement: IEvenement | null = null;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ evenement }) => {
      this.evenement = evenement;
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  previousState(): void {
    window.history.back();
  }
}
