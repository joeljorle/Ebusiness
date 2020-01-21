import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICartproducts } from 'app/shared/model/cartproducts.model';

@Component({
  selector: 'jhi-cartproducts-detail',
  templateUrl: './cartproducts-detail.component.html'
})
export class CartproductsDetailComponent implements OnInit {
  cartproducts: ICartproducts | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cartproducts }) => {
      this.cartproducts = cartproducts;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
