import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IRatings, Ratings } from 'app/shared/model/ratings.model';
import { RatingsService } from './ratings.service';
import { ICategory } from 'app/shared/model/category.model';
import { CategoryService } from 'app/entities/category/category.service';
import { IProduct } from 'app/shared/model/product.model';
import { ProductService } from 'app/entities/product/product.service';
import { IEvenement } from 'app/shared/model/evenement.model';
import { EvenementService } from 'app/entities/evenement/evenement.service';

type SelectableEntity = ICategory | IProduct | IEvenement;

@Component({
  selector: 'jhi-ratings-update',
  templateUrl: './ratings-update.component.html'
})
export class RatingsUpdateComponent implements OnInit {
  isSaving = false;
  categories: ICategory[] = [];
  products: IProduct[] = [];
  evenements: IEvenement[] = [];

  editForm = this.fb.group({
    id: [],
    slug: [null, [Validators.required]],
    userid: [null, [Validators.required]],
    categoryid: [],
    productid: [],
    tourid: [],
    tourgroupid: [],
    evenementid: [],
    value: [null, [Validators.min(0)]],
    createdat: [],
    updatedat: [],
    categoryId: [],
    productId: [],
    evenementId: []
  });

  constructor(
    protected ratingsService: RatingsService,
    protected categoryService: CategoryService,
    protected productService: ProductService,
    protected evenementService: EvenementService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ratings }) => {
      if (!ratings.id) {
        const today = moment().startOf('day');
        ratings.createdat = today;
        ratings.updatedat = today;
      }

      this.updateForm(ratings);

      this.categoryService.query().subscribe((res: HttpResponse<ICategory[]>) => (this.categories = res.body || []));

      this.productService.query().subscribe((res: HttpResponse<IProduct[]>) => (this.products = res.body || []));

      this.evenementService.query().subscribe((res: HttpResponse<IEvenement[]>) => (this.evenements = res.body || []));
    });
  }

  updateForm(ratings: IRatings): void {
    this.editForm.patchValue({
      id: ratings.id,
      slug: ratings.slug,
      userid: ratings.userid,
      categoryid: ratings.categoryid,
      productid: ratings.productid,
      tourid: ratings.tourid,
      tourgroupid: ratings.tourgroupid,
      evenementid: ratings.evenementid,
      value: ratings.value,
      createdat: ratings.createdat ? ratings.createdat.format(DATE_TIME_FORMAT) : null,
      updatedat: ratings.updatedat ? ratings.updatedat.format(DATE_TIME_FORMAT) : null,
      categoryId: ratings.categoryId,
      productId: ratings.productId,
      evenementId: ratings.evenementId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ratings = this.createFromForm();
    if (ratings.id !== undefined) {
      this.subscribeToSaveResponse(this.ratingsService.update(ratings));
    } else {
      this.subscribeToSaveResponse(this.ratingsService.create(ratings));
    }
  }

  private createFromForm(): IRatings {
    return {
      ...new Ratings(),
      id: this.editForm.get(['id'])!.value,
      slug: this.editForm.get(['slug'])!.value,
      userid: this.editForm.get(['userid'])!.value,
      categoryid: this.editForm.get(['categoryid'])!.value,
      productid: this.editForm.get(['productid'])!.value,
      tourid: this.editForm.get(['tourid'])!.value,
      tourgroupid: this.editForm.get(['tourgroupid'])!.value,
      evenementid: this.editForm.get(['evenementid'])!.value,
      value: this.editForm.get(['value'])!.value,
      createdat: this.editForm.get(['createdat'])!.value ? moment(this.editForm.get(['createdat'])!.value, DATE_TIME_FORMAT) : undefined,
      updatedat: this.editForm.get(['updatedat'])!.value ? moment(this.editForm.get(['updatedat'])!.value, DATE_TIME_FORMAT) : undefined,
      categoryId: this.editForm.get(['categoryId'])!.value,
      productId: this.editForm.get(['productId'])!.value,
      evenementId: this.editForm.get(['evenementId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRatings>>): void {
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
