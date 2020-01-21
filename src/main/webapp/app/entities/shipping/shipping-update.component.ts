import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IShipping, Shipping } from 'app/shared/model/shipping.model';
import { ShippingService } from './shipping.service';
import { ICategory } from 'app/shared/model/category.model';
import { CategoryService } from 'app/entities/category/category.service';

@Component({
  selector: 'jhi-shipping-update',
  templateUrl: './shipping-update.component.html'
})
export class ShippingUpdateComponent implements OnInit {
  isSaving = false;

  categories: ICategory[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    price: [],
    createdat: [],
    updatedat: [],
    categoryId: []
  });

  constructor(
    protected shippingService: ShippingService,
    protected categoryService: CategoryService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ shipping }) => {
      this.updateForm(shipping);

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

  updateForm(shipping: IShipping): void {
    this.editForm.patchValue({
      id: shipping.id,
      name: shipping.name,
      price: shipping.price,
      createdat: shipping.createdat != null ? shipping.createdat.format(DATE_TIME_FORMAT) : null,
      updatedat: shipping.updatedat != null ? shipping.updatedat.format(DATE_TIME_FORMAT) : null,
      categoryId: shipping.categoryId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const shipping = this.createFromForm();
    if (shipping.id !== undefined) {
      this.subscribeToSaveResponse(this.shippingService.update(shipping));
    } else {
      this.subscribeToSaveResponse(this.shippingService.create(shipping));
    }
  }

  private createFromForm(): IShipping {
    return {
      ...new Shipping(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      price: this.editForm.get(['price'])!.value,
      createdat:
        this.editForm.get(['createdat'])!.value != null ? moment(this.editForm.get(['createdat'])!.value, DATE_TIME_FORMAT) : undefined,
      updatedat:
        this.editForm.get(['updatedat'])!.value != null ? moment(this.editForm.get(['updatedat'])!.value, DATE_TIME_FORMAT) : undefined,
      categoryId: this.editForm.get(['categoryId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IShipping>>): void {
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

  trackById(index: number, item: ICategory): any {
    return item.id;
  }
}
