import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ICherche, Cherche } from 'app/shared/model/cherche.model';
import { ChercheService } from './cherche.service';

@Component({
  selector: 'jhi-cherche-update',
  templateUrl: './cherche-update.component.html'
})
export class ChercheUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    slug: [null, [Validators.required]],
    name: [],
    abrev: [],
    userid: [],
    details: [],
    createdat: [],
    updatedat: []
  });

  constructor(protected chercheService: ChercheService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cherche }) => {
      if (!cherche.id) {
        const today = moment().startOf('day');
        cherche.createdat = today;
        cherche.updatedat = today;
      }

      this.updateForm(cherche);
    });
  }

  updateForm(cherche: ICherche): void {
    this.editForm.patchValue({
      id: cherche.id,
      slug: cherche.slug,
      name: cherche.name,
      abrev: cherche.abrev,
      userid: cherche.userid,
      details: cherche.details,
      createdat: cherche.createdat ? cherche.createdat.format(DATE_TIME_FORMAT) : null,
      updatedat: cherche.updatedat ? cherche.updatedat.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const cherche = this.createFromForm();
    if (cherche.id !== undefined) {
      this.subscribeToSaveResponse(this.chercheService.update(cherche));
    } else {
      this.subscribeToSaveResponse(this.chercheService.create(cherche));
    }
  }

  private createFromForm(): ICherche {
    return {
      ...new Cherche(),
      id: this.editForm.get(['id'])!.value,
      slug: this.editForm.get(['slug'])!.value,
      name: this.editForm.get(['name'])!.value,
      abrev: this.editForm.get(['abrev'])!.value,
      userid: this.editForm.get(['userid'])!.value,
      details: this.editForm.get(['details'])!.value,
      createdat: this.editForm.get(['createdat'])!.value ? moment(this.editForm.get(['createdat'])!.value, DATE_TIME_FORMAT) : undefined,
      updatedat: this.editForm.get(['updatedat'])!.value ? moment(this.editForm.get(['updatedat'])!.value, DATE_TIME_FORMAT) : undefined
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICherche>>): void {
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
