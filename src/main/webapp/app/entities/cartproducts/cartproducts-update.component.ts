import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ICartproducts, Cartproducts } from 'app/shared/model/cartproducts.model';
import { CartproductsService } from './cartproducts.service';
import { ICart } from 'app/shared/model/cart.model';
import { CartService } from 'app/entities/cart/cart.service';

@Component({
  selector: 'jhi-cartproducts-update',
  templateUrl: './cartproducts-update.component.html'
})
export class CartproductsUpdateComponent implements OnInit {
  isSaving = false;

  carts: ICart[] = [];

  editForm = this.fb.group({
    id: [],
    cartid: [null, [Validators.required]],
    productid: [null, [Validators.required]],
    cartqty: [null, [Validators.min(0)]],
    cartId: []
  });

  constructor(
    protected cartproductsService: CartproductsService,
    protected cartService: CartService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cartproducts }) => {
      this.updateForm(cartproducts);

      this.cartService
        .query()
        .pipe(
          map((res: HttpResponse<ICart[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: ICart[]) => (this.carts = resBody));
    });
  }

  updateForm(cartproducts: ICartproducts): void {
    this.editForm.patchValue({
      id: cartproducts.id,
      cartid: cartproducts.cartid,
      productid: cartproducts.productid,
      cartqty: cartproducts.cartqty,
      cartId: cartproducts.cartId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const cartproducts = this.createFromForm();
    if (cartproducts.id !== undefined) {
      this.subscribeToSaveResponse(this.cartproductsService.update(cartproducts));
    } else {
      this.subscribeToSaveResponse(this.cartproductsService.create(cartproducts));
    }
  }

  private createFromForm(): ICartproducts {
    return {
      ...new Cartproducts(),
      id: this.editForm.get(['id'])!.value,
      cartid: this.editForm.get(['cartid'])!.value,
      productid: this.editForm.get(['productid'])!.value,
      cartqty: this.editForm.get(['cartqty'])!.value,
      cartId: this.editForm.get(['cartId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICartproducts>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: ICart): any {
    return item.id;
  }
}
