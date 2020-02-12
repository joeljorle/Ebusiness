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

import { ITourcategory, Tourcategory } from 'app/shared/model/tourcategory.model';
import { TourcategoryService } from './tourcategory.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { IFiles } from 'app/shared/model/files.model';
import { FilesService } from 'app/entities/files/files.service';
import { ICategory } from 'app/shared/model/category.model';
import { CategoryService } from 'app/entities/category/category.service';
import { IProduct } from 'app/shared/model/product.model';
import { ProductService } from 'app/entities/product/product.service';
import { IEvenement } from 'app/shared/model/evenement.model';
import { EvenementService } from 'app/entities/evenement/evenement.service';

type SelectableEntity = IFiles | ICategory | IProduct | IEvenement;

@Component({
  selector: 'jhi-tourcategory-update',
  templateUrl: './tourcategory-update.component.html'
})
export class TourcategoryUpdateComponent implements OnInit {
  isSaving = false;
  files: IFiles[] = [];
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
    othercontacts: [],
    otherfields: [],
    createdat: [],
    updatedat: [],
    filesId: [],
    categoryId: [],
    productId: [],
    evenementId: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected tourcategoryService: TourcategoryService,
    protected filesService: FilesService,
    protected categoryService: CategoryService,
    protected productService: ProductService,
    protected evenementService: EvenementService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tourcategory }) => {
      if (!tourcategory.id) {
        const today = moment().startOf('day');
        tourcategory.lockdelay = today;
        tourcategory.createdat = today;
        tourcategory.updatedat = today;
      }

      this.updateForm(tourcategory);

      this.filesService
        .query({ filter: 'tourcategory-is-null' })
        .pipe(
          map((res: HttpResponse<IFiles[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IFiles[]) => {
          if (!tourcategory.filesId) {
            this.files = resBody;
          } else {
            this.filesService
              .find(tourcategory.filesId)
              .pipe(
                map((subRes: HttpResponse<IFiles>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IFiles[]) => (this.files = concatRes));
          }
        });

      this.categoryService.query().subscribe((res: HttpResponse<ICategory[]>) => (this.categories = res.body || []));

      this.productService.query().subscribe((res: HttpResponse<IProduct[]>) => (this.products = res.body || []));

      this.evenementService.query().subscribe((res: HttpResponse<IEvenement[]>) => (this.evenements = res.body || []));
    });
  }

  updateForm(tourcategory: ITourcategory): void {
    this.editForm.patchValue({
      id: tourcategory.id,
      categoryid: tourcategory.categoryid,
      productid: tourcategory.productid,
      evenementid: tourcategory.evenementid,
      slug: tourcategory.slug,
      name: tourcategory.name,
      islock: tourcategory.islock,
      lockdelay: tourcategory.lockdelay ? tourcategory.lockdelay.format(DATE_TIME_FORMAT) : null,
      about: tourcategory.about,
      title: tourcategory.title,
      tag: tourcategory.tag,
      tagcolor: tourcategory.tagcolor,
      postalcode: tourcategory.postalcode,
      phones: tourcategory.phones,
      website: tourcategory.website,
      facebook: tourcategory.facebook,
      twitter: tourcategory.twitter,
      gplus: tourcategory.gplus,
      linkedin: tourcategory.linkedin,
      instagram: tourcategory.instagram,
      email: tourcategory.email,
      othercontacts: tourcategory.othercontacts,
      otherfields: tourcategory.otherfields,
      createdat: tourcategory.createdat ? tourcategory.createdat.format(DATE_TIME_FORMAT) : null,
      updatedat: tourcategory.updatedat ? tourcategory.updatedat.format(DATE_TIME_FORMAT) : null,
      filesId: tourcategory.filesId,
      categoryId: tourcategory.categoryId,
      productId: tourcategory.productId,
      evenementId: tourcategory.evenementId
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
    const tourcategory = this.createFromForm();
    if (tourcategory.id !== undefined) {
      this.subscribeToSaveResponse(this.tourcategoryService.update(tourcategory));
    } else {
      this.subscribeToSaveResponse(this.tourcategoryService.create(tourcategory));
    }
  }

  private createFromForm(): ITourcategory {
    return {
      ...new Tourcategory(),
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
      othercontacts: this.editForm.get(['othercontacts'])!.value,
      otherfields: this.editForm.get(['otherfields'])!.value,
      createdat: this.editForm.get(['createdat'])!.value ? moment(this.editForm.get(['createdat'])!.value, DATE_TIME_FORMAT) : undefined,
      updatedat: this.editForm.get(['updatedat'])!.value ? moment(this.editForm.get(['updatedat'])!.value, DATE_TIME_FORMAT) : undefined,
      filesId: this.editForm.get(['filesId'])!.value,
      categoryId: this.editForm.get(['categoryId'])!.value,
      productId: this.editForm.get(['productId'])!.value,
      evenementId: this.editForm.get(['evenementId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITourcategory>>): void {
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
