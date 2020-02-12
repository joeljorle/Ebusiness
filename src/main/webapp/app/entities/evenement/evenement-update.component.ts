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

import { IEvenement, Evenement } from 'app/shared/model/evenement.model';
import { EvenementService } from './evenement.service';
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

type SelectableEntity = IFiles | ICurrency | ILocation | ICategory | IProduct;

@Component({
  selector: 'jhi-evenement-update',
  templateUrl: './evenement-update.component.html'
})
export class EvenementUpdateComponent implements OnInit {
  isSaving = false;
  files: IFiles[] = [];
  currencies: ICurrency[] = [];
  locations: ILocation[] = [];
  categories: ICategory[] = [];
  products: IProduct[] = [];

  editForm = this.fb.group({
    id: [],
    categoryid: [],
    userid: [],
    productid: [],
    slug: [null, [Validators.required]],
    name: [],
    islock: [],
    lockdelay: [],
    islimited: [],
    limiteddelay: [],
    limitedbooking: [],
    startdate: [],
    enddate: [],
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
    othercontacts: [],
    otherfields: [],
    createdat: [],
    updatedat: [],
    filesId: [],
    currencyId: [],
    locationId: [],
    categoryId: [],
    productId: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected evenementService: EvenementService,
    protected filesService: FilesService,
    protected currencyService: CurrencyService,
    protected locationService: LocationService,
    protected categoryService: CategoryService,
    protected productService: ProductService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ evenement }) => {
      if (!evenement.id) {
        const today = moment().startOf('day');
        evenement.lockdelay = today;
        evenement.limiteddelay = today;
        evenement.startdate = today;
        evenement.enddate = today;
        evenement.createdat = today;
        evenement.updatedat = today;
      }

      this.updateForm(evenement);

      this.filesService
        .query({ filter: 'evenement-is-null' })
        .pipe(
          map((res: HttpResponse<IFiles[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IFiles[]) => {
          if (!evenement.filesId) {
            this.files = resBody;
          } else {
            this.filesService
              .find(evenement.filesId)
              .pipe(
                map((subRes: HttpResponse<IFiles>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IFiles[]) => (this.files = concatRes));
          }
        });

      this.currencyService
        .query({ filter: 'evenement-is-null' })
        .pipe(
          map((res: HttpResponse<ICurrency[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ICurrency[]) => {
          if (!evenement.currencyId) {
            this.currencies = resBody;
          } else {
            this.currencyService
              .find(evenement.currencyId)
              .pipe(
                map((subRes: HttpResponse<ICurrency>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ICurrency[]) => (this.currencies = concatRes));
          }
        });

      this.locationService
        .query({ filter: 'evenement-is-null' })
        .pipe(
          map((res: HttpResponse<ILocation[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ILocation[]) => {
          if (!evenement.locationId) {
            this.locations = resBody;
          } else {
            this.locationService
              .find(evenement.locationId)
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
    });
  }

  updateForm(evenement: IEvenement): void {
    this.editForm.patchValue({
      id: evenement.id,
      categoryid: evenement.categoryid,
      userid: evenement.userid,
      productid: evenement.productid,
      slug: evenement.slug,
      name: evenement.name,
      islock: evenement.islock,
      lockdelay: evenement.lockdelay ? evenement.lockdelay.format(DATE_TIME_FORMAT) : null,
      islimited: evenement.islimited,
      limiteddelay: evenement.limiteddelay ? evenement.limiteddelay.format(DATE_TIME_FORMAT) : null,
      limitedbooking: evenement.limitedbooking,
      startdate: evenement.startdate ? evenement.startdate.format(DATE_TIME_FORMAT) : null,
      enddate: evenement.enddate ? evenement.enddate.format(DATE_TIME_FORMAT) : null,
      about: evenement.about,
      title: evenement.title,
      tag: evenement.tag,
      tagcolor: evenement.tagcolor,
      postalcode: evenement.postalcode,
      phones: evenement.phones,
      website: evenement.website,
      facebook: evenement.facebook,
      twitter: evenement.twitter,
      gplus: evenement.gplus,
      linkedin: evenement.linkedin,
      instagram: evenement.instagram,
      email: evenement.email,
      url: evenement.url,
      othercontacts: evenement.othercontacts,
      otherfields: evenement.otherfields,
      createdat: evenement.createdat ? evenement.createdat.format(DATE_TIME_FORMAT) : null,
      updatedat: evenement.updatedat ? evenement.updatedat.format(DATE_TIME_FORMAT) : null,
      filesId: evenement.filesId,
      currencyId: evenement.currencyId,
      locationId: evenement.locationId,
      categoryId: evenement.categoryId,
      productId: evenement.productId
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
    const evenement = this.createFromForm();
    if (evenement.id !== undefined) {
      this.subscribeToSaveResponse(this.evenementService.update(evenement));
    } else {
      this.subscribeToSaveResponse(this.evenementService.create(evenement));
    }
  }

  private createFromForm(): IEvenement {
    return {
      ...new Evenement(),
      id: this.editForm.get(['id'])!.value,
      categoryid: this.editForm.get(['categoryid'])!.value,
      userid: this.editForm.get(['userid'])!.value,
      productid: this.editForm.get(['productid'])!.value,
      slug: this.editForm.get(['slug'])!.value,
      name: this.editForm.get(['name'])!.value,
      islock: this.editForm.get(['islock'])!.value,
      lockdelay: this.editForm.get(['lockdelay'])!.value ? moment(this.editForm.get(['lockdelay'])!.value, DATE_TIME_FORMAT) : undefined,
      islimited: this.editForm.get(['islimited'])!.value,
      limiteddelay: this.editForm.get(['limiteddelay'])!.value
        ? moment(this.editForm.get(['limiteddelay'])!.value, DATE_TIME_FORMAT)
        : undefined,
      limitedbooking: this.editForm.get(['limitedbooking'])!.value,
      startdate: this.editForm.get(['startdate'])!.value ? moment(this.editForm.get(['startdate'])!.value, DATE_TIME_FORMAT) : undefined,
      enddate: this.editForm.get(['enddate'])!.value ? moment(this.editForm.get(['enddate'])!.value, DATE_TIME_FORMAT) : undefined,
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
      othercontacts: this.editForm.get(['othercontacts'])!.value,
      otherfields: this.editForm.get(['otherfields'])!.value,
      createdat: this.editForm.get(['createdat'])!.value ? moment(this.editForm.get(['createdat'])!.value, DATE_TIME_FORMAT) : undefined,
      updatedat: this.editForm.get(['updatedat'])!.value ? moment(this.editForm.get(['updatedat'])!.value, DATE_TIME_FORMAT) : undefined,
      filesId: this.editForm.get(['filesId'])!.value,
      currencyId: this.editForm.get(['currencyId'])!.value,
      locationId: this.editForm.get(['locationId'])!.value,
      categoryId: this.editForm.get(['categoryId'])!.value,
      productId: this.editForm.get(['productId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEvenement>>): void {
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
