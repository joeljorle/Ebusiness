import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IPaymentcategory, Paymentcategory } from 'app/shared/model/paymentcategory.model';
import { PaymentcategoryService } from './paymentcategory.service';
import { ICategory } from 'app/shared/model/category.model';
import { CategoryService } from 'app/entities/category/category.service';
import { IPaymentmethod } from 'app/shared/model/paymentmethod.model';
import { PaymentmethodService } from 'app/entities/paymentmethod/paymentmethod.service';

type SelectableEntity = ICategory | IPaymentmethod;

@Component({
  selector: 'jhi-paymentcategory-update',
  templateUrl: './paymentcategory-update.component.html'
})
export class PaymentcategoryUpdateComponent implements OnInit {
  isSaving = false;
  categories: ICategory[] = [];
  paymentmethods: IPaymentmethod[] = [];

  editForm = this.fb.group({
    id: [],
    paymentmethodid: [],
    categoryid: [null, [Validators.required]],
    url: [],
    apiurl: [],
    apikey: [],
    key2: [],
    key3: [],
    key4: [],
    phonenumber: [],
    chanel: [],
    code: [],
    username: [],
    password: [],
    createdat: [],
    updatedat: [],
    categoryId: [],
    paymentmethodId: []
  });

  constructor(
    protected paymentcategoryService: PaymentcategoryService,
    protected categoryService: CategoryService,
    protected paymentmethodService: PaymentmethodService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ paymentcategory }) => {
      if (!paymentcategory.id) {
        const today = moment().startOf('day');
        paymentcategory.createdat = today;
        paymentcategory.updatedat = today;
      }

      this.updateForm(paymentcategory);

      this.categoryService.query().subscribe((res: HttpResponse<ICategory[]>) => (this.categories = res.body || []));

      this.paymentmethodService.query().subscribe((res: HttpResponse<IPaymentmethod[]>) => (this.paymentmethods = res.body || []));
    });
  }

  updateForm(paymentcategory: IPaymentcategory): void {
    this.editForm.patchValue({
      id: paymentcategory.id,
      paymentmethodid: paymentcategory.paymentmethodid,
      categoryid: paymentcategory.categoryid,
      url: paymentcategory.url,
      apiurl: paymentcategory.apiurl,
      apikey: paymentcategory.apikey,
      key2: paymentcategory.key2,
      key3: paymentcategory.key3,
      key4: paymentcategory.key4,
      phonenumber: paymentcategory.phonenumber,
      chanel: paymentcategory.chanel,
      code: paymentcategory.code,
      username: paymentcategory.username,
      password: paymentcategory.password,
      createdat: paymentcategory.createdat ? paymentcategory.createdat.format(DATE_TIME_FORMAT) : null,
      updatedat: paymentcategory.updatedat ? paymentcategory.updatedat.format(DATE_TIME_FORMAT) : null,
      categoryId: paymentcategory.categoryId,
      paymentmethodId: paymentcategory.paymentmethodId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const paymentcategory = this.createFromForm();
    if (paymentcategory.id !== undefined) {
      this.subscribeToSaveResponse(this.paymentcategoryService.update(paymentcategory));
    } else {
      this.subscribeToSaveResponse(this.paymentcategoryService.create(paymentcategory));
    }
  }

  private createFromForm(): IPaymentcategory {
    return {
      ...new Paymentcategory(),
      id: this.editForm.get(['id'])!.value,
      paymentmethodid: this.editForm.get(['paymentmethodid'])!.value,
      categoryid: this.editForm.get(['categoryid'])!.value,
      url: this.editForm.get(['url'])!.value,
      apiurl: this.editForm.get(['apiurl'])!.value,
      apikey: this.editForm.get(['apikey'])!.value,
      key2: this.editForm.get(['key2'])!.value,
      key3: this.editForm.get(['key3'])!.value,
      key4: this.editForm.get(['key4'])!.value,
      phonenumber: this.editForm.get(['phonenumber'])!.value,
      chanel: this.editForm.get(['chanel'])!.value,
      code: this.editForm.get(['code'])!.value,
      username: this.editForm.get(['username'])!.value,
      password: this.editForm.get(['password'])!.value,
      createdat: this.editForm.get(['createdat'])!.value ? moment(this.editForm.get(['createdat'])!.value, DATE_TIME_FORMAT) : undefined,
      updatedat: this.editForm.get(['updatedat'])!.value ? moment(this.editForm.get(['updatedat'])!.value, DATE_TIME_FORMAT) : undefined,
      categoryId: this.editForm.get(['categoryId'])!.value,
      paymentmethodId: this.editForm.get(['paymentmethodId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPaymentcategory>>): void {
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
