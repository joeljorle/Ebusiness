import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IFiles, Files } from 'app/shared/model/files.model';
import { FilesService } from './files.service';

@Component({
  selector: 'jhi-files-update',
  templateUrl: './files-update.component.html'
})
export class FilesUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    slug: [null, [Validators.required]],
    userid: [],
    categoryid: [],
    productid: [],
    tourid: [],
    tourgroupid: [],
    tourcategoryid: [],
    evenementid: [],
    islogoimg: [],
    isprofileimg: [],
    iscoverimg: [],
    issliderimg: [],
    isotherimg: [],
    createdat: [],
    updatedat: []
  });

  constructor(protected filesService: FilesService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ files }) => {
      this.updateForm(files);
    });
  }

  updateForm(files: IFiles): void {
    this.editForm.patchValue({
      id: files.id,
      slug: files.slug,
      userid: files.userid,
      categoryid: files.categoryid,
      productid: files.productid,
      tourid: files.tourid,
      tourgroupid: files.tourgroupid,
      tourcategoryid: files.tourcategoryid,
      evenementid: files.evenementid,
      islogoimg: files.islogoimg,
      isprofileimg: files.isprofileimg,
      iscoverimg: files.iscoverimg,
      issliderimg: files.issliderimg,
      isotherimg: files.isotherimg,
      createdat: files.createdat != null ? files.createdat.format(DATE_TIME_FORMAT) : null,
      updatedat: files.updatedat != null ? files.updatedat.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const files = this.createFromForm();
    if (files.id !== undefined) {
      this.subscribeToSaveResponse(this.filesService.update(files));
    } else {
      this.subscribeToSaveResponse(this.filesService.create(files));
    }
  }

  private createFromForm(): IFiles {
    return {
      ...new Files(),
      id: this.editForm.get(['id'])!.value,
      slug: this.editForm.get(['slug'])!.value,
      userid: this.editForm.get(['userid'])!.value,
      categoryid: this.editForm.get(['categoryid'])!.value,
      productid: this.editForm.get(['productid'])!.value,
      tourid: this.editForm.get(['tourid'])!.value,
      tourgroupid: this.editForm.get(['tourgroupid'])!.value,
      tourcategoryid: this.editForm.get(['tourcategoryid'])!.value,
      evenementid: this.editForm.get(['evenementid'])!.value,
      islogoimg: this.editForm.get(['islogoimg'])!.value,
      isprofileimg: this.editForm.get(['isprofileimg'])!.value,
      iscoverimg: this.editForm.get(['iscoverimg'])!.value,
      issliderimg: this.editForm.get(['issliderimg'])!.value,
      isotherimg: this.editForm.get(['isotherimg'])!.value,
      createdat:
        this.editForm.get(['createdat'])!.value != null ? moment(this.editForm.get(['createdat'])!.value, DATE_TIME_FORMAT) : undefined,
      updatedat:
        this.editForm.get(['updatedat'])!.value != null ? moment(this.editForm.get(['updatedat'])!.value, DATE_TIME_FORMAT) : undefined
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFiles>>): void {
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
