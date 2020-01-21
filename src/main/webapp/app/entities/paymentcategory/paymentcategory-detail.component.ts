import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPaymentcategory } from 'app/shared/model/paymentcategory.model';

@Component({
  selector: 'jhi-paymentcategory-detail',
  templateUrl: './paymentcategory-detail.component.html'
})
export class PaymentcategoryDetailComponent implements OnInit {
  paymentcategory: IPaymentcategory | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ paymentcategory }) => {
      this.paymentcategory = paymentcategory;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
