import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IProduct, Product } from 'app/shared/model/product.model';
import { ProductService } from './product.service';
import { IFiles } from 'app/shared/model/files.model';
import { FilesService } from 'app/entities/files/files.service';
import { IProductdetails } from 'app/shared/model/productdetails.model';
import { ProductdetailsService } from 'app/entities/productdetails/productdetails.service';
import { ICurrency } from 'app/shared/model/currency.model';
import { CurrencyService } from 'app/entities/currency/currency.service';
import { ILocation } from 'app/shared/model/location.model';
import { LocationService } from 'app/entities/location/location.service';
import { ICategory } from 'app/shared/model/category.model';
import { CategoryService } from 'app/entities/category/category.service';

type SelectableEntity = IFiles | IProductdetails | ICurrency | ILocation | ICategory;

@Component({
  selector: 'jhi-product-update',
  templateUrl: './product-update.component.html'
})
export class ProductUpdateComponent implements OnInit {
  isSaving = false;

  files: IFiles[] = [];

  productdetails: IProductdetails[] = [];

  currencies: ICurrency[] = [];

  locations: ILocation[] = [];

  categories: ICategory[] = [];

  editForm = this.fb.group({
    id: [],
    categoryid: [null, [Validators.required]],
    slug: [null, [Validators.required]],
    name: [],
    islock: [],
    lockdelay: [],
    type: [],
    createdat: [],
    updatedat: [],
    filesId: [],
    productdetailsId: [],
    currencyId: [],
    locationId: [],
    categoryId: []
  });

  constructor(
    protected productService: ProductService,
    protected filesService: FilesService,
    protected productdetailsService: ProductdetailsService,
    protected currencyService: CurrencyService,
    protected locationService: LocationService,
    protected categoryService: CategoryService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ product }) => {
      this.updateForm(product);

      this.filesService
        .query({ filter: 'product-is-null' })
        .pipe(
          map((res: HttpResponse<IFiles[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IFiles[]) => {
          if (!product.filesId) {
            this.files = resBody;
          } else {
            this.filesService
              .find(product.filesId)
              .pipe(
                map((subRes: HttpResponse<IFiles>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IFiles[]) => {
                this.files = concatRes;
              });
          }
        });

      this.productdetailsService
        .query({ filter: 'product-is-null' })
        .pipe(
          map((res: HttpResponse<IProductdetails[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IProductdetails[]) => {
          if (!product.productdetailsId) {
            this.productdetails = resBody;
          } else {
            this.productdetailsService
              .find(product.productdetailsId)
              .pipe(
                map((subRes: HttpResponse<IProductdetails>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IProductdetails[]) => {
                this.productdetails = concatRes;
              });
          }
        });

      this.currencyService
        .query({ filter: 'product-is-null' })
        .pipe(
          map((res: HttpResponse<ICurrency[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: ICurrency[]) => {
          if (!product.currencyId) {
            this.currencies = resBody;
          } else {
            this.currencyService
              .find(product.currencyId)
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

      this.locationService
        .query({ filter: 'product-is-null' })
        .pipe(
          map((res: HttpResponse<ILocation[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: ILocation[]) => {
          if (!product.locationId) {
            this.locations = resBody;
          } else {
            this.locationService
              .find(product.locationId)
              .pipe(
                map((subRes: HttpResponse<ILocation>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ILocation[]) => {
                this.locations = concatRes;
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

  updateForm(product: IProduct): void {
    this.editForm.patchValue({
      id: product.id,
      categoryid: product.categoryid,
      slug: product.slug,
      name: product.name,
      islock: product.islock,
      lockdelay: product.lockdelay != null ? product.lockdelay.format(DATE_TIME_FORMAT) : null,
      type: product.type,
      createdat: product.createdat != null ? product.createdat.format(DATE_TIME_FORMAT) : null,
      updatedat: product.updatedat != null ? product.updatedat.format(DATE_TIME_FORMAT) : null,
      filesId: product.filesId,
      productdetailsId: product.productdetailsId,
      currencyId: product.currencyId,
      locationId: product.locationId,
      categoryId: product.categoryId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const product = this.createFromForm();
    if (product.id !== undefined) {
      this.subscribeToSaveResponse(this.productService.update(product));
    } else {
      this.subscribeToSaveResponse(this.productService.create(product));
    }
  }

  private createFromForm(): IProduct {
    return {
      ...new Product(),
      id: this.editForm.get(['id'])!.value,
      categoryid: this.editForm.get(['categoryid'])!.value,
      slug: this.editForm.get(['slug'])!.value,
      name: this.editForm.get(['name'])!.value,
      islock: this.editForm.get(['islock'])!.value,
      lockdelay:
        this.editForm.get(['lockdelay'])!.value != null ? moment(this.editForm.get(['lockdelay'])!.value, DATE_TIME_FORMAT) : undefined,
      type: this.editForm.get(['type'])!.value,
      createdat:
        this.editForm.get(['createdat'])!.value != null ? moment(this.editForm.get(['createdat'])!.value, DATE_TIME_FORMAT) : undefined,
      updatedat:
        this.editForm.get(['updatedat'])!.value != null ? moment(this.editForm.get(['updatedat'])!.value, DATE_TIME_FORMAT) : undefined,
      filesId: this.editForm.get(['filesId'])!.value,
      productdetailsId: this.editForm.get(['productdetailsId'])!.value,
      currencyId: this.editForm.get(['currencyId'])!.value,
      locationId: this.editForm.get(['locationId'])!.value,
      categoryId: this.editForm.get(['categoryId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProduct>>): void {
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
