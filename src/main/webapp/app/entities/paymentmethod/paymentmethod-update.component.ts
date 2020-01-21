import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IPaymentmethod, Paymentmethod } from 'app/shared/model/paymentmethod.model';
import { PaymentmethodService } from './paymentmethod.service';
import { AlertError } from 'app/shared/alert/alert-error.model';

@Component({
  selector: 'jhi-paymentmethod-update',
  templateUrl: './paymentmethod-update.component.html'
})
export class PaymentmethodUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    slug: [null, [Validators.required]],
    name: [],
    logo: [],
    logodata: [],
    logodataContentType: [],
    createdat: [],
    updatedat: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected paymentmethodService: PaymentmethodService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ paymentmethod }) => {
      this.updateForm(paymentmethod);
    });
  }

  updateForm(paymentmethod: IPaymentmethod): void {
    this.editForm.patchValue({
      id: paymentmethod.id,
      slug: paymentmethod.slug,
      name: paymentmethod.name,
      logo: paymentmethod.logo,
      logodata: paymentmethod.logodata,
      logodataContentType: paymentmethod.logodataContentType,
      createdat: paymentmethod.createdat != null ? paymentmethod.createdat.format(DATE_TIME_FORMAT) : null,
      updatedat: paymentmethod.updatedat != null ? paymentmethod.updatedat.format(DATE_TIME_FORMAT) : null
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('ebusinesApp.error', { ...err, key: 'error.file.' + err.key })
      );
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const paymentmethod = this.createFromForm();
    if (paymentmethod.id !== undefined) {
      this.subscribeToSaveResponse(this.paymentmethodService.update(paymentmethod));
    } else {
      this.subscribeToSaveResponse(this.paymentmethodService.create(paymentmethod));
    }
  }

  private createFromForm(): IPaymentmethod {
    return {
      ...new Paymentmethod(),
      id: this.editForm.get(['id'])!.value,
      slug: this.editForm.get(['slug'])!.value,
      name: this.editForm.get(['name'])!.value,
      logo: this.editForm.get(['logo'])!.value,
      logodataContentType: this.editForm.get(['logodataContentType'])!.value,
      logodata: this.editForm.get(['logodata'])!.value,
      createdat:
        this.editForm.get(['createdat'])!.value != null ? moment(this.editForm.get(['createdat'])!.value, DATE_TIME_FORMAT) : undefined,
      updatedat:
        this.editForm.get(['updatedat'])!.value != null ? moment(this.editForm.get(['updatedat'])!.value, DATE_TIME_FORMAT) : undefined
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPaymentmethod>>): void {
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
}
