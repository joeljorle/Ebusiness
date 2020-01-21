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

import { ITour, Tour } from 'app/shared/model/tour.model';
import { TourService } from './tour.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { IFiles } from 'app/shared/model/files.model';
import { FilesService } from 'app/entities/files/files.service';
import { ICurrency } from 'app/shared/model/currency.model';
import { CurrencyService } from 'app/entities/currency/currency.service';
import { ILocation } from 'app/shared/model/location.model';
import { LocationService } from 'app/entities/location/location.service';
import { ICategory } from 'app/shared/model/category.model';
import { CategoryService } from 'app/entities/category/category.service';
import { IProduct } from 'app/shared/model/product.model';
import { ProductService } from 'app/entities/product/product.service';
import { IEvenement } from 'app/shared/model/evenement.model';
import { EvenementService } from 'app/entities/evenement/evenement.service';

type SelectableEntity = IFiles | ICurrency | ILocation | ICategory | IProduct | IEvenement;

@Component({
  selector: 'jhi-tour-update',
  templateUrl: './tour-update.component.html'
})
export class TourUpdateComponent implements OnInit {
  isSaving = false;

  files: IFiles[] = [];

  currencies: ICurrency[] = [];

  locations: ILocation[] = [];

  categories: ICategory[] = [];

  products: IProduct[] = [];

  evenements: IEvenement[] = [];

  editForm = this.fb.group({
    id: [],
    categoryid: [],
    userid: [],
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
    url: [],
    position: [],
    othercontacts: [],
    otherfields: [],
    createdat: [],
    updatedat: [],
    filesId: [],
    currencyId: [],
    locationId: [],
    categoryId: [],
    productId: [],
    evenementId: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected tourService: TourService,
    protected filesService: FilesService,
    protected currencyService: CurrencyService,
    protected locationService: LocationService,
    protected categoryService: CategoryService,
    protected productService: ProductService,
    protected evenementService: EvenementService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tour }) => {
      this.updateForm(tour);

      this.filesService
        .query({ filter: 'tour-is-null' })
        .pipe(
          map((res: HttpResponse<IFiles[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IFiles[]) => {
          if (!tour.filesId) {
            this.files = resBody;
          } else {
            this.filesService
              .find(tour.filesId)
              .pipe(
                map((subRes: HttpResponse<IFiles>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IFiles[]) => {
                this.files = concatRes;
              });
          }
        });

      this.currencyService
        .query({ filter: 'tour-is-null' })
        .pipe(
          map((res: HttpResponse<ICurrency[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: ICurrency[]) => {
          if (!tour.currencyId) {
            this.currencies = resBody;
          } else {
            this.currencyService
              .find(tour.currencyId)
              .pipe(
                map((subRes: HttpResponse<ICurrency>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ICurrency[]) => {
                this.currencies = concatRes;
              });
          }
        });

      this.locationService
        .query({ filter: 'tour-is-null' })
        .pipe(
          map((res: HttpResponse<ILocation[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: ILocation[]) => {
          if (!tour.locationId) {
            this.locations = resBody;
          } else {
            this.locationService
              .find(tour.locationId)
              .pipe(
                map((subRes: HttpResponse<ILocation>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ILocation[]) => {
                this.locations = concatRes;
              });
          }
        });

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

  updateForm(tour: ITour): void {
    this.editForm.patchValue({
      id: tour.id,
      categoryid: tour.categoryid,
      userid: tour.userid,
      productid: tour.productid,
      evenementid: tour.evenementid,
      slug: tour.slug,
      name: tour.name,
      islock: tour.islock,
      lockdelay: tour.lockdelay != null ? tour.lockdelay.format(DATE_TIME_FORMAT) : null,
      about: tour.about,
      title: tour.title,
      tag: tour.tag,
      tagcolor: tour.tagcolor,
      postalcode: tour.postalcode,
      phones: tour.phones,
      website: tour.website,
      facebook: tour.facebook,
      twitter: tour.twitter,
      gplus: tour.gplus,
      linkedin: tour.linkedin,
      instagram: tour.instagram,
      email: tour.email,
      url: tour.url,
      position: tour.position,
      othercontacts: tour.othercontacts,
      otherfields: tour.otherfields,
      createdat: tour.createdat != null ? tour.createdat.format(DATE_TIME_FORMAT) : null,
      updatedat: tour.updatedat != null ? tour.updatedat.format(DATE_TIME_FORMAT) : null,
      filesId: tour.filesId,
      currencyId: tour.currencyId,
      locationId: tour.locationId,
      categoryId: tour.categoryId,
      productId: tour.productId,
      evenementId: tour.evenementId
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
    const tour = this.createFromForm();
    if (tour.id !== undefined) {
      this.subscribeToSaveResponse(this.tourService.update(tour));
    } else {
      this.subscribeToSaveResponse(this.tourService.create(tour));
    }
  }

  private createFromForm(): ITour {
    return {
      ...new Tour(),
      id: this.editForm.get(['id'])!.value,
      categoryid: this.editForm.get(['categoryid'])!.value,
      userid: this.editForm.get(['userid'])!.value,
      productid: this.editForm.get(['productid'])!.value,
      evenementid: this.editForm.get(['evenementid'])!.value,
      slug: this.editForm.get(['slug'])!.value,
      name: this.editForm.get(['name'])!.value,
      islock: this.editForm.get(['islock'])!.value,
      lockdelay:
        this.editForm.get(['lockdelay'])!.value != null ? moment(this.editForm.get(['lockdelay'])!.value, DATE_TIME_FORMAT) : undefined,
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
      url: this.editForm.get(['url'])!.value,
      position: this.editForm.get(['position'])!.value,
      othercontacts: this.editForm.get(['othercontacts'])!.value,
      otherfields: this.editForm.get(['otherfields'])!.value,
      createdat:
        this.editForm.get(['createdat'])!.value != null ? moment(this.editForm.get(['createdat'])!.value, DATE_TIME_FORMAT) : undefined,
      updatedat:
        this.editForm.get(['updatedat'])!.value != null ? moment(this.editForm.get(['updatedat'])!.value, DATE_TIME_FORMAT) : undefined,
      filesId: this.editForm.get(['filesId'])!.value,
      currencyId: this.editForm.get(['currencyId'])!.value,
      locationId: this.editForm.get(['locationId'])!.value,
      categoryId: this.editForm.get(['categoryId'])!.value,
      productId: this.editForm.get(['productId'])!.value,
      evenementId: this.editForm.get(['evenementId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITour>>): void {
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
