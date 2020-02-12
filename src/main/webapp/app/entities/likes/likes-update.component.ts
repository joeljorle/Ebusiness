import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ILikes, Likes } from 'app/shared/model/likes.model';
import { LikesService } from './likes.service';
import { ICategory } from 'app/shared/model/category.model';
import { CategoryService } from 'app/entities/category/category.service';
import { IProduct } from 'app/shared/model/product.model';
import { ProductService } from 'app/entities/product/product.service';
import { IEvenement } from 'app/shared/model/evenement.model';
import { EvenementService } from 'app/entities/evenement/evenement.service';

type SelectableEntity = ICategory | IProduct | IEvenement;

@Component({
  selector: 'jhi-likes-update',
  templateUrl: './likes-update.component.html'
})
export class LikesUpdateComponent implements OnInit {
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
    createdat: [],
    updatedat: [],
    categoryId: [],
    productId: [],
    evenementId: []
  });

  constructor(
    protected likesService: LikesService,
    protected categoryService: CategoryService,
    protected productService: ProductService,
    protected evenementService: EvenementService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ likes }) => {
      if (!likes.id) {
        const today = moment().startOf('day');
        likes.createdat = today;
        likes.updatedat = today;
      }

      this.updateForm(likes);

      this.categoryService.query().subscribe((res: HttpResponse<ICategory[]>) => (this.categories = res.body || []));

      this.productService.query().subscribe((res: HttpResponse<IProduct[]>) => (this.products = res.body || []));

      this.evenementService.query().subscribe((res: HttpResponse<IEvenement[]>) => (this.evenements = res.body || []));
    });
  }

  updateForm(likes: ILikes): void {
    this.editForm.patchValue({
      id: likes.id,
      slug: likes.slug,
      userid: likes.userid,
      categoryid: likes.categoryid,
      productid: likes.productid,
      tourid: likes.tourid,
      tourgroupid: likes.tourgroupid,
      evenementid: likes.evenementid,
      createdat: likes.createdat ? likes.createdat.format(DATE_TIME_FORMAT) : null,
      updatedat: likes.updatedat ? likes.updatedat.format(DATE_TIME_FORMAT) : null,
      categoryId: likes.categoryId,
      productId: likes.productId,
      evenementId: likes.evenementId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const likes = this.createFromForm();
    if (likes.id !== undefined) {
      this.subscribeToSaveResponse(this.likesService.update(likes));
    } else {
      this.subscribeToSaveResponse(this.likesService.create(likes));
    }
  }

  private createFromForm(): ILikes {
    return {
      ...new Likes(),
      id: this.editForm.get(['id'])!.value,
      slug: this.editForm.get(['slug'])!.value,
      userid: this.editForm.get(['userid'])!.value,
      categoryid: this.editForm.get(['categoryid'])!.value,
      productid: this.editForm.get(['productid'])!.value,
      tourid: this.editForm.get(['tourid'])!.value,
      tourgroupid: this.editForm.get(['tourgroupid'])!.value,
      evenementid: this.editForm.get(['evenementid'])!.value,
      createdat: this.editForm.get(['createdat'])!.value ? moment(this.editForm.get(['createdat'])!.value, DATE_TIME_FORMAT) : undefined,
      updatedat: this.editForm.get(['updatedat'])!.value ? moment(this.editForm.get(['updatedat'])!.value, DATE_TIME_FORMAT) : undefined,
      categoryId: this.editForm.get(['categoryId'])!.value,
      productId: this.editForm.get(['productId'])!.value,
      evenementId: this.editForm.get(['evenementId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILikes>>): void {
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
