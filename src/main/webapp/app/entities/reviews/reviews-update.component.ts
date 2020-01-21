import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IReviews, Reviews } from 'app/shared/model/reviews.model';
import { ReviewsService } from './reviews.service';
import { ICategory } from 'app/shared/model/category.model';
import { CategoryService } from 'app/entities/category/category.service';
import { IProduct } from 'app/shared/model/product.model';
import { ProductService } from 'app/entities/product/product.service';
import { IEvenement } from 'app/shared/model/evenement.model';
import { EvenementService } from 'app/entities/evenement/evenement.service';

type SelectableEntity = ICategory | IProduct | IEvenement;

@Component({
  selector: 'jhi-reviews-update',
  templateUrl: './reviews-update.component.html'
})
export class ReviewsUpdateComponent implements OnInit {
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
    text: [],
    createdat: [],
    updatedat: [],
    categoryId: [],
    productId: [],
    evenementId: []
  });

  constructor(
    protected reviewsService: ReviewsService,
    protected categoryService: CategoryService,
    protected productService: ProductService,
    protected evenementService: EvenementService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ reviews }) => {
      this.updateForm(reviews);

      this.categoryService
        .query()
        .pipe(
          map((res: HttpResponse<ICategory[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: ICategory[]) => (this.categories = resBody));

      this.productService
        .query()
        .pipe(
          map((res: HttpResponse<IProduct[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IProduct[]) => (this.products = resBody));

      this.evenementService
        .query()
        .pipe(
          map((res: HttpResponse<IEvenement[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IEvenement[]) => (this.evenements = resBody));
    });
  }

  updateForm(reviews: IReviews): void {
    this.editForm.patchValue({
      id: reviews.id,
      slug: reviews.slug,
      userid: reviews.userid,
      categoryid: reviews.categoryid,
      productid: reviews.productid,
      tourid: reviews.tourid,
      tourgroupid: reviews.tourgroupid,
      evenementid: reviews.evenementid,
      text: reviews.text,
      createdat: reviews.createdat != null ? reviews.createdat.format(DATE_TIME_FORMAT) : null,
      updatedat: reviews.updatedat != null ? reviews.updatedat.format(DATE_TIME_FORMAT) : null,
      categoryId: reviews.categoryId,
      productId: reviews.productId,
      evenementId: reviews.evenementId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const reviews = this.createFromForm();
    if (reviews.id !== undefined) {
      this.subscribeToSaveResponse(this.reviewsService.update(reviews));
    } else {
      this.subscribeToSaveResponse(this.reviewsService.create(reviews));
    }
  }

  private createFromForm(): IReviews {
    return {
      ...new Reviews(),
      id: this.editForm.get(['id'])!.value,
      slug: this.editForm.get(['slug'])!.value,
      userid: this.editForm.get(['userid'])!.value,
      categoryid: this.editForm.get(['categoryid'])!.value,
      productid: this.editForm.get(['productid'])!.value,
      tourid: this.editForm.get(['tourid'])!.value,
      tourgroupid: this.editForm.get(['tourgroupid'])!.value,
      evenementid: this.editForm.get(['evenementid'])!.value,
      text: this.editForm.get(['text'])!.value,
      createdat:
        this.editForm.get(['createdat'])!.value != null ? moment(this.editForm.get(['createdat'])!.value, DATE_TIME_FORMAT) : undefined,
      updatedat:
        this.editForm.get(['updatedat'])!.value != null ? moment(this.editForm.get(['updatedat'])!.value, DATE_TIME_FORMAT) : undefined,
      categoryId: this.editForm.get(['categoryId'])!.value,
      productId: this.editForm.get(['productId'])!.value,
      evenementId: this.editForm.get(['evenementId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IReviews>>): void {
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
