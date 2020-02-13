import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { ITourgroup, Tourgroup } from 'app/shared/model/tourgroup.model';
import { TourgroupService } from './tourgroup.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { IFiles } from 'app/shared/model/files.model';
import { FilesService } from 'app/entities/files/files.service';
import { ILocation } from 'app/shared/model/location.model';
import { LocationService } from 'app/entities/location/location.service';
import { ICategory } from 'app/shared/model/category.model';
import { CategoryService } from 'app/entities/category/category.service';
import { IProduct } from 'app/shared/model/product.model';
import { ProductService } from 'app/entities/product/product.service';
import { IEvenement } from 'app/shared/model/evenement.model';
import { EvenementService } from 'app/entities/evenement/evenement.service';

type SelectableEntity = IFiles | ILocation | ICategory | IProduct | IEvenement;

@Component({
  selector: 'jhi-tourgroup-update',
  templateUrl: './tourgroup-update.component.html'
})
export class TourgroupUpdateComponent implements OnInit {
  isSaving = false;
  files: IFiles[] = [];
  locations: ILocation[] = [];
  categories: ICategory[] = [];
  products: IProduct[] = [];
  evenements: IEvenement[] = [];

  editForm = this.fb.group({
    id: [],
    categoryid: [],
    productid: [],
    evenementid: [],
    slug: [null, [Validators.required]],
    name: [],
    islock: [],
    lockdelay: [],
    about: [],
    title: [],
    tag: [],
    tagcolor: [],
    postalcode: [],
    phones: [],
    website: [],
    facebook: [],
    twitter: [],
    gplus: [],
    linkedin: [],
    instagram: [],
    email: [],
    position: [],
    othercontacts: [],
    otherfields: [],
    createdat: [],
    updatedat: [],
    filesId: [],
    locationId: [],
    categoryId: [],
    productId: [],
    evenementId: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected tourgroupService: TourgroupService,
    protected filesService: FilesService,
    protected locationService: LocationService,
    protected categoryService: CategoryService,
    protected productService: ProductService,
    protected evenementService: EvenementService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tourgroup }) => {
      if (!tourgroup.id) {
        const today = moment().startOf('day');
        tourgroup.lockdelay = today;
        tourgroup.createdat = today;
        tourgroup.updatedat = today;
      }

      this.updateForm(tourgroup);

      this.filesService
        .query({ filter: 'tourgroup-is-null' })
        .pipe(
          map((res: HttpResponse<IFiles[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IFiles[]) => {
          if (!tourgroup.filesId) {
            this.files = resBody;
          } else {
            this.filesService
              .find(tourgroup.filesId)
              .pipe(
                map((subRes: HttpResponse<IFiles>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IFiles[]) => (this.files = concatRes));
          }
        });

      this.locationService
        .query({ filter: 'tourgroup-is-null' })
        .pipe(
          map((res: HttpResponse<ILocation[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ILocation[]) => {
          if (!tourgroup.locationId) {
            this.locations = resBody;
          } else {
            this.locationService
              .find(tourgroup.locationId)
              .pipe(
                map((subRes: HttpResponse<ILocation>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ILocation[]) => (this.locations = concatRes));
          }
        });

      this.categoryService.query().subscribe((res: HttpResponse<ICategory[]>) => (this.categories = res.body || []));

      this.productService.query().subscribe((res: HttpResponse<IProduct[]>) => (this.products = res.body || []));

      this.evenementService.query().subscribe((res: HttpResponse<IEvenement[]>) => (this.evenements = res.body || []));
    });
  }

  updateForm(tourgroup: ITourgroup): void {
    this.editForm.patchValue({
      id: tourgroup.id,
      categoryid: tourgroup.categoryid,
      productid: tourgroup.productid,
      evenementid: tourgroup.evenementid,
      slug: tourgroup.slug,
      name: tourgroup.name,
      islock: tourgroup.islock,
      lockdelay: tourgroup.lockdelay ? tourgroup.lockdelay.format(DATE_TIME_FORMAT) : null,
      about: tourgroup.about,
      title: tourgroup.title,
      tag: tourgroup.tag,
      tagcolor: tourgroup.tagcolor,
      postalcode: tourgroup.postalcode,
      phones: tourgroup.phones,
      website: tourgroup.website,
      facebook: tourgroup.facebook,
      twitter: tourgroup.twitter,
      gplus: tourgroup.gplus,
      linkedin: tourgroup.linkedin,
      instagram: tourgroup.instagram,
      email: tourgroup.email,
      position: tourgroup.position,
      othercontacts: tourgroup.othercontacts,
      otherfields: tourgroup.otherfields,
      createdat: tourgroup.createdat ? tourgroup.createdat.format(DATE_TIME_FORMAT) : null,
      updatedat: tourgroup.updatedat ? tourgroup.updatedat.format(DATE_TIME_FORMAT) : null,
      filesId: tourgroup.filesId,
      locationId: tourgroup.locationId,
      categoryId: tourgroup.categoryId,
      productId: tourgroup.productId,
      evenementId: tourgroup.evenementId
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
        new JhiEventWithContent<AlertError>('ebusinessApp.error', { ...err, key: 'error.file.' + err.key })
      );
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tourgroup = this.createFromForm();
    if (tourgroup.id !== undefined) {
      this.subscribeToSaveResponse(this.tourgroupService.update(tourgroup));
    } else {
      this.subscribeToSaveResponse(this.tourgroupService.create(tourgroup));
    }
  }

  private createFromForm(): ITourgroup {
    return {
      ...new Tourgroup(),
      id: this.editForm.get(['id'])!.value,
      categoryid: this.editForm.get(['categoryid'])!.value,
      productid: this.editForm.get(['productid'])!.value,
      evenementid: this.editForm.get(['evenementid'])!.value,
      slug: this.editForm.get(['slug'])!.value,
      name: this.editForm.get(['name'])!.value,
      islock: this.editForm.get(['islock'])!.value,
      lockdelay: this.editForm.get(['lockdelay'])!.value ? moment(this.editForm.get(['lockdelay'])!.value, DATE_TIME_FORMAT) : undefined,
      about: this.editForm.get(['about'])!.value,
      title: this.editForm.get(['title'])!.value,
      tag: this.editForm.get(['tag'])!.value,
      tagcolor: this.editForm.get(['tagcolor'])!.value,
      postalcode: this.editForm.get(['postalcode'])!.value,
      phones: this.editForm.get(['phones'])!.value,
      website: this.editForm.get(['website'])!.value,
      facebook: this.editForm.get(['facebook'])!.value,
      twitter: this.editForm.get(['twitter'])!.value,
      gplus: this.editForm.get(['gplus'])!.value,
      linkedin: this.editForm.get(['linkedin'])!.value,
      instagram: this.editForm.get(['instagram'])!.value,
      email: this.editForm.get(['email'])!.value,
      position: this.editForm.get(['position'])!.value,
      othercontacts: this.editForm.get(['othercontacts'])!.value,
      otherfields: this.editForm.get(['otherfields'])!.value,
      createdat: this.editForm.get(['createdat'])!.value ? moment(this.editForm.get(['createdat'])!.value, DATE_TIME_FORMAT) : undefined,
      updatedat: this.editForm.get(['updatedat'])!.value ? moment(this.editForm.get(['updatedat'])!.value, DATE_TIME_FORMAT) : undefined,
      filesId: this.editForm.get(['filesId'])!.value,
      locationId: this.editForm.get(['locationId'])!.value,
      categoryId: this.editForm.get(['categoryId'])!.value,
      productId: this.editForm.get(['productId'])!.value,
      evenementId: this.editForm.get(['evenementId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITourgroup>>): void {
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
