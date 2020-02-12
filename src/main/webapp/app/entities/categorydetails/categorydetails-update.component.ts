import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { ICategorydetails, Categorydetails } from 'app/shared/model/categorydetails.model';
import { CategorydetailsService } from './categorydetails.service';
import { AlertError } from 'app/shared/alert/alert-error.model';

@Component({
  selector: 'jhi-categorydetails-update',
  templateUrl: './categorydetails-update.component.html'
})
export class CategorydetailsUpdateComponent implements OnInit {
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
    generalabout: [],
    generaltax: [],
    generalstock: [],
    generalprice: [],
    generalmaxstock: [],
    generaltag: [],
    generaltagcolor: [],
    generalhidden: [],
    generalhiddendelay: [],
    generallock: [],
    generallockdelay: [],
    generalexpire: [],
    generalexpiredelay: [],
    childsname: [],
    productsname: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected categorydetailsService: CategorydetailsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ categorydetails }) => {
      if (!categorydetails.id) {
        const today = moment().startOf('day');
        categorydetails.generalhiddendelay = today;
        categorydetails.generallockdelay = today;
        categorydetails.generalexpiredelay = today;
      }

      this.updateForm(categorydetails);
    });
  }

  updateForm(categorydetails: ICategorydetails): void {
    this.editForm.patchValue({
      id: categorydetails.id,
      about: categorydetails.about,
      title: categorydetails.title,
      tag: categorydetails.tag,
      tagcolor: categorydetails.tagcolor,
      defaultlanguage: categorydetails.defaultlanguage,
      postalcode: categorydetails.postalcode,
      phones: categorydetails.phones,
      website: categorydetails.website,
      email: categorydetails.email,
      facebook: categorydetails.facebook,
      twitter: categorydetails.twitter,
      gplus: categorydetails.gplus,
      linkedin: categorydetails.linkedin,
      instagram: categorydetails.instagram,
      opentimes: categorydetails.opentimes,
      othercontacts: categorydetails.othercontacts,
      otherfields: categorydetails.otherfields,
      generalabout: categorydetails.generalabout,
      generaltax: categorydetails.generaltax,
      generalstock: categorydetails.generalstock,
      generalprice: categorydetails.generalprice,
      generalmaxstock: categorydetails.generalmaxstock,
      generaltag: categorydetails.generaltag,
      generaltagcolor: categorydetails.generaltagcolor,
      generalhidden: categorydetails.generalhidden,
      generalhiddendelay: categorydetails.generalhiddendelay ? categorydetails.generalhiddendelay.format(DATE_TIME_FORMAT) : null,
      generallock: categorydetails.generallock,
      generallockdelay: categorydetails.generallockdelay ? categorydetails.generallockdelay.format(DATE_TIME_FORMAT) : null,
      generalexpire: categorydetails.generalexpire,
      generalexpiredelay: categorydetails.generalexpiredelay ? categorydetails.generalexpiredelay.format(DATE_TIME_FORMAT) : null,
      childsname: categorydetails.childsname,
      productsname: categorydetails.productsname
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
    const categorydetails = this.createFromForm();
    if (categorydetails.id !== undefined) {
      this.subscribeToSaveResponse(this.categorydetailsService.update(categorydetails));
    } else {
      this.subscribeToSaveResponse(this.categorydetailsService.create(categorydetails));
    }
  }

  private createFromForm(): ICategorydetails {
    return {
      ...new Categorydetails(),
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
      generalabout: this.editForm.get(['generalabout'])!.value,
      generaltax: this.editForm.get(['generaltax'])!.value,
      generalstock: this.editForm.get(['generalstock'])!.value,
      generalprice: this.editForm.get(['generalprice'])!.value,
      generalmaxstock: this.editForm.get(['generalmaxstock'])!.value,
      generaltag: this.editForm.get(['generaltag'])!.value,
      generaltagcolor: this.editForm.get(['generaltagcolor'])!.value,
      generalhidden: this.editForm.get(['generalhidden'])!.value,
      generalhiddendelay: this.editForm.get(['generalhiddendelay'])!.value
        ? moment(this.editForm.get(['generalhiddendelay'])!.value, DATE_TIME_FORMAT)
        : undefined,
      generallock: this.editForm.get(['generallock'])!.value,
      generallockdelay: this.editForm.get(['generallockdelay'])!.value
        ? moment(this.editForm.get(['generallockdelay'])!.value, DATE_TIME_FORMAT)
        : undefined,
      generalexpire: this.editForm.get(['generalexpire'])!.value,
      generalexpiredelay: this.editForm.get(['generalexpiredelay'])!.value
        ? moment(this.editForm.get(['generalexpiredelay'])!.value, DATE_TIME_FORMAT)
        : undefined,
      childsname: this.editForm.get(['childsname'])!.value,
      productsname: this.editForm.get(['productsname'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICategorydetails>>): void {
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
