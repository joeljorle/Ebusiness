import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ICart, Cart } from 'app/shared/model/cart.model';
import { CartService } from './cart.service';
import { ICurrency } from 'app/shared/model/currency.model';
import { CurrencyService } from 'app/entities/currency/currency.service';
import { ICategory } from 'app/shared/model/category.model';
import { CategoryService } from 'app/entities/category/category.service';

type SelectableEntity = ICurrency | ICategory;

@Component({
  selector: 'jhi-cart-update',
  templateUrl: './cart-update.component.html'
})
export class CartUpdateComponent implements OnInit {
  isSaving = false;

  currencies: ICurrency[] = [];

  categories: ICategory[] = [];

  editForm = this.fb.group({
    id: [],
    slug: [],
    categoryid: [],
    userid: [],
    totalprice: [],
    createdat: [],
    updatedat: [],
    currencyId: [],
    categoryId: []
  });

  constructor(
    protected cartService: CartService,
    protected currencyService: CurrencyService,
    protected categoryService: CategoryService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cart }) => {
      this.updateForm(cart);

      this.currencyService
        .query({ filter: 'cart-is-null' })
        .pipe(
          map((res: HttpResponse<ICurrency[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: ICurrency[]) => {
          if (!cart.currencyId) {
            this.currencies = resBody;
          } else {
            this.currencyService
              .find(cart.currencyId)
              .pipe(
                map((subRes: HttpResponse<ICurrency>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ICurrency[]) => {
                this.currencies = concatRes;
              });
          }
        });

      this.categoryService
        .query()
        .pipe(
          map((res: HttpResponse<ICategory[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: ICategory[]) => (this.categories = resBody));
    });
  }

  updateForm(cart: ICart): void {
    this.editForm.patchValue({
      id: cart.id,
      slug: cart.slug,
      categoryid: cart.categoryid,
      userid: cart.userid,
      totalprice: cart.totalprice,
      createdat: cart.createdat != null ? cart.createdat.format(DATE_TIME_FORMAT) : null,
      updatedat: cart.updatedat != null ? cart.updatedat.format(DATE_TIME_FORMAT) : null,
      currencyId: cart.currencyId,
      categoryId: cart.categoryId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const cart = this.createFromForm();
    if (cart.id !== undefined) {
      this.subscribeToSaveResponse(this.cartService.update(cart));
    } else {
      this.subscribeToSaveResponse(this.cartService.create(cart));
    }
  }

  private createFromForm(): ICart {
    return {
      ...new Cart(),
      id: this.editForm.get(['id'])!.value,
      slug: this.editForm.get(['slug'])!.value,
      categoryid: this.editForm.get(['categoryid'])!.value,
      userid: this.editForm.get(['userid'])!.value,
      totalprice: this.editForm.get(['totalprice'])!.value,
      createdat:
        this.editForm.get(['createdat'])!.value != null ? moment(this.editForm.get(['createdat'])!.value, DATE_TIME_FORMAT) : undefined,
      updatedat:
        this.editForm.get(['updatedat'])!.value != null ? moment(this.editForm.get(['updatedat'])!.value, DATE_TIME_FORMAT) : undefined,
      currencyId: this.editForm.get(['currencyId'])!.value,
      categoryId: this.editForm.get(['categoryId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICart>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
