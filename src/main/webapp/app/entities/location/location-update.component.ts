import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ILocation, Location } from 'app/shared/model/location.model';
import { LocationService } from './location.service';

@Component({
  selector: 'jhi-location-update',
  templateUrl: './location-update.component.html'
})
export class LocationUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    country: [],
    town: [],
    region: [],
    locality: [],
    zone: [],
    lattitude: [],
    longitude: [],
    createdat: [],
    updatedat: []
  });

  constructor(protected locationService: LocationService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ location }) => {
      this.updateForm(location);
    });
  }

  updateForm(location: ILocation): void {
    this.editForm.patchValue({
      id: location.id,
      country: location.country,
      town: location.town,
      region: location.region,
      locality: location.locality,
      zone: location.zone,
      lattitude: location.lattitude,
      longitude: location.longitude,
      createdat: location.createdat != null ? location.createdat.format(DATE_TIME_FORMAT) : null,
      updatedat: location.updatedat != null ? location.updatedat.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const location = this.createFromForm();
    if (location.id !== undefined) {
      this.subscribeToSaveResponse(this.locationService.update(location));
    } else {
      this.subscribeToSaveResponse(this.locationService.create(location));
    }
  }

  private createFromForm(): ILocation {
    return {
      ...new Location(),
      id: this.editForm.get(['id'])!.value,
      country: this.editForm.get(['country'])!.value,
      town: this.editForm.get(['town'])!.value,
      region: this.editForm.get(['region'])!.value,
      locality: this.editForm.get(['locality'])!.value,
      zone: this.editForm.get(['zone'])!.value,
      lattitude: this.editForm.get(['lattitude'])!.value,
      longitude: this.editForm.get(['longitude'])!.value,
      createdat:
        this.editForm.get(['createdat'])!.value != null ? moment(this.editForm.get(['createdat'])!.value, DATE_TIME_FORMAT) : undefined,
      updatedat:
        this.editForm.get(['updatedat'])!.value != null ? moment(this.editForm.get(['updatedat'])!.value, DATE_TIME_FORMAT) : undefined
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILocation>>): void {
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
