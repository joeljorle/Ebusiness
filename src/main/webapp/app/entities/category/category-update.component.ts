import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ICategory, Category } from 'app/shared/model/category.model';
import { CategoryService } from './category.service';
import { IFiles } from 'app/shared/model/files.model';
import { FilesService } from 'app/entities/files/files.service';
import { ICategorydetails } from 'app/shared/model/categorydetails.model';
import { CategorydetailsService } from 'app/entities/categorydetails/categorydetails.service';
import { ILocation } from 'app/shared/model/location.model';
import { LocationService } from 'app/entities/location/location.service';

type SelectableEntity = IFiles | ICategorydetails | ILocation | ICategory;

@Component({
  selector: 'jhi-category-update',
  templateUrl: './category-update.component.html'
})
export class CategoryUpdateComponent implements OnInit {
  isSaving = false;

  files: IFiles[] = [];

  categorydetails: ICategorydetails[] = [];

  locations: ILocation[] = [];

  categories: ICategory[] = [];

  editForm = this.fb.group({
    id: [],
    slug: [null, [Validators.required]],
    userid: [],
    categoryid: [],
    name: [],
    route: [],
    isheader: [],
    isgroup: [],
    islock: [],
    lockdelay: [],
    type: [],
    createdat: [],
    updatedat: [],
    filesId: [],
    categorydetailsId: [],
    locationId: [],
    categoryId: []
  });

  constructor(
    protected categoryService: CategoryService,
    protected filesService: FilesService,
    protected categorydetailsService: CategorydetailsService,
    protected locationService: LocationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ category }) => {
      this.updateForm(category);

      this.filesService
        .query({ filter: 'category-is-null' })
        .pipe(
          map((res: HttpResponse<IFiles[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IFiles[]) => {
          if (!category.filesId) {
            this.files = resBody;
          } else {
            this.filesService
              .find(category.filesId)
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

      this.categorydetailsService
        .query({ filter: 'category-is-null' })
        .pipe(
          map((res: HttpResponse<ICategorydetails[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: ICategorydetails[]) => {
          if (!category.categorydetailsId) {
            this.categorydetails = resBody;
          } else {
            this.categorydetailsService
              .find(category.categorydetailsId)
              .pipe(
                map((subRes: HttpResponse<ICategorydetails>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ICategorydetails[]) => {
                this.categorydetails = concatRes;
              });
          }
        });

      this.locationService
        .query({ filter: 'category-is-null' })
        .pipe(
          map((res: HttpResponse<ILocation[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: ILocation[]) => {
          if (!category.locationId) {
            this.locations = resBody;
          } else {
            this.locationService
              .find(category.locationId)
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
    });
  }

  updateForm(category: ICategory): void {
    this.editForm.patchValue({
      id: category.id,
      slug: category.slug,
      userid: category.userid,
      categoryid: category.categoryid,
      name: category.name,
      route: category.route,
      isheader: category.isheader,
      isgroup: category.isgroup,
      islock: category.islock,
      lockdelay: category.lockdelay != null ? category.lockdelay.format(DATE_TIME_FORMAT) : null,
      type: category.type,
      createdat: category.createdat != null ? category.createdat.format(DATE_TIME_FORMAT) : null,
      updatedat: category.updatedat != null ? category.updatedat.format(DATE_TIME_FORMAT) : null,
      filesId: category.filesId,
      categorydetailsId: category.categorydetailsId,
      locationId: category.locationId,
      categoryId: category.categoryId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const category = this.createFromForm();
    if (category.id !== undefined) {
      this.subscribeToSaveResponse(this.categoryService.update(category));
    } else {
      this.subscribeToSaveResponse(this.categoryService.create(category));
    }
  }

  private createFromForm(): ICategory {
    return {
      ...new Category(),
      id: this.editForm.get(['id'])!.value,
      slug: this.editForm.get(['slug'])!.value,
      userid: this.editForm.get(['userid'])!.value,
      categoryid: this.editForm.get(['categoryid'])!.value,
      name: this.editForm.get(['name'])!.value,
      route: this.editForm.get(['route'])!.value,
      isheader: this.editForm.get(['isheader'])!.value,
      isgroup: this.editForm.get(['isgroup'])!.value,
      islock: this.editForm.get(['islock'])!.value,
      lockdelay:
        this.editForm.get(['lockdelay'])!.value != null ? moment(this.editForm.get(['lockdelay'])!.value, DATE_TIME_FORMAT) : undefined,
      type: this.editForm.get(['type'])!.value,
      createdat:
        this.editForm.get(['createdat'])!.value != null ? moment(this.editForm.get(['createdat'])!.value, DATE_TIME_FORMAT) : undefined,
      updatedat:
        this.editForm.get(['updatedat'])!.value != null ? moment(this.editForm.get(['updatedat'])!.value, DATE_TIME_FORMAT) : undefined,
      filesId: this.editForm.get(['filesId'])!.value,
      categorydetailsId: this.editForm.get(['categorydetailsId'])!.value,
      locationId: this.editForm.get(['locationId'])!.value,
      categoryId: this.editForm.get(['categoryId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICategory>>): void {
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
