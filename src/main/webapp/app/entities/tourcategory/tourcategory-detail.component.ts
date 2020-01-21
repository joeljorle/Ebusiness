import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { ITourcategory } from 'app/shared/model/tourcategory.model';

@Component({
  selector: 'jhi-tourcategory-detail',
  templateUrl: './tourcategory-detail.component.html'
})
export class TourcategoryDetailComponent implements OnInit {
  tourcategory: ITourcategory | null = null;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tourcategory }) => {
      this.tourcategory = tourcategory;
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
