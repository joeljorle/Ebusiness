import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IProductdetails, Productdetails } from 'app/shared/model/productdetails.model';
import { ProductdetailsService } from './productdetails.service';
import { AlertError } from 'app/shared/alert/alert-error.model';

@Component({
  selector: 'jhi-productdetails-update',
  templateUrl: './productdetails-update.component.html'
})
export class ProductdetailsUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    about: [],
    title: [],
    tag: [],
    tagcolor: [],
    defaultlanguage: [],
    postalcode: [],
    phones: [],
    website: [],
    email: [],
    facebook: [],
    twitter: [],
    gplus: [],
    linkedin: [],
    instagram: [],
    opentimes: [],
    othercontacts: [],
    otherfields: [],
    sizes: [],
    colors: [],
    models: [],
    shippingprice: [],
    serialnumber: [],
    tax: [],
    stock: [],
    price: [],
    maxqty: [],
    availableat: [],
    lockactiondelay: [],
    lockaction: [],
    expireat: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected productdetailsService: ProductdetailsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ productdetails }) => {
      if (!productdetails.id) {
        const today = moment().startOf('day');
        productdetails.availableat = today;
        productdetails.lockactiondelay = today;
        productdetails.expireat = today;
      }

      this.updateForm(productdetails);
    });
  }

  updateForm(productdetails: IProductdetails): void {
    this.editForm.patchValue({
      id: productdetails.id,
      about: productdetails.about,
      title: productdetails.title,
      tag: productdetails.tag,
      tagcolor: productdetails.tagcolor,
      defaultlanguage: productdetails.defaultlanguage,
      postalcode: productdetails.postalcode,
      phones: productdetails.phones,
      website: productdetails.website,
      email: productdetails.email,
      facebook: productdetails.facebook,
      twitter: productdetails.twitter,
      gplus: productdetails.gplus,
      linkedin: productdetails.linkedin,
      instagram: productdetails.instagram,
      opentimes: productdetails.opentimes,
      othercontacts: productdetails.othercontacts,
      otherfields: productdetails.otherfields,
      sizes: productdetails.sizes,
      colors: productdetails.colors,
      models: productdetails.models,
      shippingprice: productdetails.shippingprice,
      serialnumber: productdetails.serialnumber,
      tax: productdetails.tax,
      stock: productdetails.stock,
      price: productdetails.price,
      maxqty: productdetails.maxqty,
      availableat: productdetails.availableat ? productdetails.availableat.format(DATE_TIME_FORMAT) : null,
      lockactiondelay: productdetails.lockactiondelay ? productdetails.lockactiondelay.format(DATE_TIME_FORMAT) : null,
      lockaction: productdetails.lockaction,
      expireat: productdetails.expireat ? productdetails.expireat.format(DATE_TIME_FORMAT) : null
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
        new JhiEventWithContent<AlertError>('eBusinessApp.error', { message: err.message })
      );
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const productdetails = this.createFromForm();
    if (productdetails.id !== undefined) {
      this.subscribeToSaveResponse(this.productdetailsService.update(productdetails));
    } else {
      this.subscribeToSaveResponse(this.productdetailsService.create(productdetails));
    }
  }

  private createFromForm(): IProductdetails {
    return {
      ...new Productdetails(),
      id: this.editForm.get(['id'])!.value,
      about: this.editForm.get(['about'])!.value,
      title: this.editForm.get(['title'])!.value,
      tag: this.editForm.get(['tag'])!.value,
      tagcolor: this.editForm.get(['tagcolor'])!.value,
      defaultlanguage: this.editForm.get(['defaultlanguage'])!.value,
      postalcode: this.editForm.get(['postalcode'])!.value,
      phones: this.editForm.get(['phones'])!.value,
      website: this.editForm.get(['website'])!.value,
      email: this.editForm.get(['email'])!.value,
      facebook: this.editForm.get(['facebook'])!.value,
      twitter: this.editForm.get(['twitter'])!.value,
      gplus: this.editForm.get(['gplus'])!.value,
      linkedin: this.editForm.get(['linkedin'])!.value,
      instagram: this.editForm.get(['instagram'])!.value,
      opentimes: this.editForm.get(['opentimes'])!.value,
      othercontacts: this.editForm.get(['othercontacts'])!.value,
      otherfields: this.editForm.get(['otherfields'])!.value,
      sizes: this.editForm.get(['sizes'])!.value,
      colors: this.editForm.get(['colors'])!.value,
      models: this.editForm.get(['models'])!.value,
      shippingprice: this.editForm.get(['shippingprice'])!.value,
      serialnumber: this.editForm.get(['serialnumber'])!.value,
      tax: this.editForm.get(['tax'])!.value,
      stock: this.editForm.get(['stock'])!.value,
      price: this.editForm.get(['price'])!.value,
      maxqty: this.editForm.get(['maxqty'])!.value,
      availableat: this.editForm.get(['availableat'])!.value
        ? moment(this.editForm.get(['availableat'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lockactiondelay: this.editForm.get(['lockactiondelay'])!.value
        ? moment(this.editForm.get(['lockactiondelay'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lockaction: this.editForm.get(['lockaction'])!.value,
      expireat: this.editForm.get(['expireat'])!.value ? moment(this.editForm.get(['expireat'])!.value, DATE_TIME_FORMAT) : undefined
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProductdetails>>): void {
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
