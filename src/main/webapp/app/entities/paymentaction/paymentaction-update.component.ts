import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IPaymentaction, Paymentaction } from 'app/shared/model/paymentaction.model';
import { PaymentactionService } from './paymentaction.service';
import { IProduct } from 'app/shared/model/product.model';
import { ProductService } from 'app/entities/product/product.service';
import { IEvenement } from 'app/shared/model/evenement.model';
import { EvenementService } from 'app/entities/evenement/evenement.service';
import { IPaymentmethod } from 'app/shared/model/paymentmethod.model';
import { PaymentmethodService } from 'app/entities/paymentmethod/paymentmethod.service';

type SelectableEntity = IProduct | IEvenement | IPaymentmethod;

@Component({
  selector: 'jhi-paymentaction-update',
  templateUrl: './paymentaction-update.component.html'
})
export class PaymentactionUpdateComponent implements OnInit {
  isSaving = false;

  products: IProduct[] = [];

  evenements: IEvenement[] = [];

  paymentmethods: IPaymentmethod[] = [];

  editForm = this.fb.group({
    id: [],
    slug: [null, [Validators.required]],
    userid: [null, [Validators.required]],
    paymentmethodid: [],
    evenementid: [],
    productid: [],
    categoryid: [],
    expireat: [],
    code: [],
    code1: [],
    code2: [],
    amount: [],
    createdat: [],
    updatedat: [],
    productId: [],
    evenementId: [],
    paymentmethodId: []
  });

  constructor(
    protected paymentactionService: PaymentactionService,
    protected productService: ProductService,
    protected evenementService: EvenementService,
    protected paymentmethodService: PaymentmethodService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ paymentaction }) => {
      this.updateForm(paymentaction);

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

      this.paymentmethodService
        .query()
        .pipe(
          map((res: HttpResponse<IPaymentmethod[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IPaymentmethod[]) => (this.paymentmethods = resBody));
    });
  }

  updateForm(paymentaction: IPaymentaction): void {
    this.editForm.patchValue({
      id: paymentaction.id,
      slug: paymentaction.slug,
      userid: paymentaction.userid,
      paymentmethodid: paymentaction.paymentmethodid,
      evenementid: paymentaction.evenementid,
      productid: paymentaction.productid,
      categoryid: paymentaction.categoryid,
      expireat: paymentaction.expireat != null ? paymentaction.expireat.format(DATE_TIME_FORMAT) : null,
      code: paymentaction.code,
      code1: paymentaction.code1,
      code2: paymentaction.code2,
      amount: paymentaction.amount,
      createdat: paymentaction.createdat != null ? paymentaction.createdat.format(DATE_TIME_FORMAT) : null,
      updatedat: paymentaction.updatedat != null ? paymentaction.updatedat.format(DATE_TIME_FORMAT) : null,
      productId: paymentaction.productId,
      evenementId: paymentaction.evenementId,
      paymentmethodId: paymentaction.paymentmethodId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const paymentaction = this.createFromForm();
    if (paymentaction.id !== undefined) {
      this.subscribeToSaveResponse(this.paymentactionService.update(paymentaction));
    } else {
      this.subscribeToSaveResponse(this.paymentactionService.create(paymentaction));
    }
  }

  private createFromForm(): IPaymentaction {
    return {
      ...new Paymentaction(),
      id: this.editForm.get(['id'])!.value,
      slug: this.editForm.get(['slug'])!.value,
      userid: this.editForm.get(['userid'])!.value,
      paymentmethodid: this.editForm.get(['paymentmethodid'])!.value,
      evenementid: this.editForm.get(['evenementid'])!.value,
      productid: this.editForm.get(['productid'])!.value,
      categoryid: this.editForm.get(['categoryid'])!.value,
      expireat:
        this.editForm.get(['expireat'])!.value != null ? moment(this.editForm.get(['expireat'])!.value, DATE_TIME_FORMAT) : undefined,
      code: this.editForm.get(['code'])!.value,
      code1: this.editForm.get(['code1'])!.value,
      code2: this.editForm.get(['code2'])!.value,
      amount: this.editForm.get(['amount'])!.value,
      createdat:
        this.editForm.get(['createdat'])!.value != null ? moment(this.editForm.get(['createdat'])!.value, DATE_TIME_FORMAT) : undefined,
      updatedat:
        this.editForm.get(['updatedat'])!.value != null ? moment(this.editForm.get(['updatedat'])!.value, DATE_TIME_FORMAT) : undefined,
      productId: this.editForm.get(['productId'])!.value,
      evenementId: this.editForm.get(['evenementId'])!.value,
      paymentmethodId: this.editForm.get(['paymentmethodId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPaymentaction>>): void {
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
