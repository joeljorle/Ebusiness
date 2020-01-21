import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IProductdetails } from 'app/shared/model/productdetails.model';

@Component({
  selector: 'jhi-productdetails-detail',
  templateUrl: './productdetails-detail.component.html'
})
export class ProductdetailsDetailComponent implements OnInit {
  productdetails: IProductdetails | null = null;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ productdetails }) => {
      this.productdetails = productdetails;
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
