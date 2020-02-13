import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { ICategorydetails } from 'app/shared/model/categorydetails.model';

@Component({
  selector: 'jhi-categorydetails-detail',
  templateUrl: './categorydetails-detail.component.html'
})
export class CategorydetailsDetailComponent implements OnInit {
  categorydetails: ICategorydetails | null = null;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ categorydetails }) => (this.categorydetails = categorydetails));
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
