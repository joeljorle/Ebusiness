import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IPaymentmethod } from 'app/shared/model/paymentmethod.model';

@Component({
  selector: 'jhi-paymentmethod-detail',
  templateUrl: './paymentmethod-detail.component.html'
})
export class PaymentmethodDetailComponent implements OnInit {
  paymentmethod: IPaymentmethod | null = null;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ paymentmethod }) => (this.paymentmethod = paymentmethod));
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
