import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IFollows, Follows } from 'app/shared/model/follows.model';
import { FollowsService } from './follows.service';
import { ICategory } from 'app/shared/model/category.model';
import { CategoryService } from 'app/entities/category/category.service';
import { IProduct } from 'app/shared/model/product.model';
import { ProductService } from 'app/entities/product/product.service';
import { IEvenement } from 'app/shared/model/evenement.model';
import { EvenementService } from 'app/entities/evenement/evenement.service';

type SelectableEntity = ICategory | IProduct | IEvenement;

@Component({
  selector: 'jhi-follows-update',
  templateUrl: './follows-update.component.html'
})
export class FollowsUpdateComponent implements OnInit {
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
    evenementid: [],
    tourgroupid: [],
    alert: [],
    alertEvenement: [],
    followchild: [],
    createdat: [],
    updatedat: [],
    categoryId: [],
    productId: [],
    evenementId: []
  });

  constructor(
    protected followsService: FollowsService,
    protected categoryService: CategoryService,
    protected productService: ProductService,
    protected evenementService: EvenementService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ follows }) => {
      if (!follows.id) {
        const today = moment().startOf('day');
        follows.createdat = today;
        follows.updatedat = today;
      }

      this.updateForm(follows);

      this.categoryService.query().subscribe((res: HttpResponse<ICategory[]>) => (this.categories = res.body || []));

      this.productService.query().subscribe((res: HttpResponse<IProduct[]>) => (this.products = res.body || []));

      this.evenementService.query().subscribe((res: HttpResponse<IEvenement[]>) => (this.evenements = res.body || []));
    });
  }

  updateForm(follows: IFollows): void {
    this.editForm.patchValue({
      id: follows.id,
      slug: follows.slug,
      userid: follows.userid,
      categoryid: follows.categoryid,
      productid: follows.productid,
      tourid: follows.tourid,
      evenementid: follows.evenementid,
      tourgroupid: follows.tourgroupid,
      alert: follows.alert,
      alertEvenement: follows.alertEvenement,
      followchild: follows.followchild,
      createdat: follows.createdat ? follows.createdat.format(DATE_TIME_FORMAT) : null,
      updatedat: follows.updatedat ? follows.updatedat.format(DATE_TIME_FORMAT) : null,
      categoryId: follows.categoryId,
      productId: follows.productId,
      evenementId: follows.evenementId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const follows = this.createFromForm();
    if (follows.id !== undefined) {
      this.subscribeToSaveResponse(this.followsService.update(follows));
    } else {
      this.subscribeToSaveResponse(this.followsService.create(follows));
    }
  }

  private createFromForm(): IFollows {
    return {
      ...new Follows(),
      id: this.editForm.get(['id'])!.value,
      slug: this.editForm.get(['slug'])!.value,
      userid: this.editForm.get(['userid'])!.value,
      categoryid: this.editForm.get(['categoryid'])!.value,
      productid: this.editForm.get(['productid'])!.value,
      tourid: this.editForm.get(['tourid'])!.value,
      evenementid: this.editForm.get(['evenementid'])!.value,
      tourgroupid: this.editForm.get(['tourgroupid'])!.value,
      alert: this.editForm.get(['alert'])!.value,
      alertEvenement: this.editForm.get(['alertEvenement'])!.value,
      followchild: this.editForm.get(['followchild'])!.value,
      createdat: this.editForm.get(['createdat'])!.value ? moment(this.editForm.get(['createdat'])!.value, DATE_TIME_FORMAT) : undefined,
      updatedat: this.editForm.get(['updatedat'])!.value ? moment(this.editForm.get(['updatedat'])!.value, DATE_TIME_FORMAT) : undefined,
      categoryId: this.editForm.get(['categoryId'])!.value,
      productId: this.editForm.get(['productId'])!.value,
      evenementId: this.editForm.get(['evenementId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFollows>>): void {
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
