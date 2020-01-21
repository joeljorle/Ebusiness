import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPaymentaction } from 'app/shared/model/paymentaction.model';

@Component({
  selector: 'jhi-paymentaction-detail',
  templateUrl: './paymentaction-detail.component.html'
})
export class PaymentactionDetailComponent implements OnInit {
  paymentaction: IPaymentaction | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ paymentaction }) => {
      this.paymentaction = paymentaction;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
