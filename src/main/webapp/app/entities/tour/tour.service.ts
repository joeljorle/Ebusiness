import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITour } from 'app/shared/model/tour.model';

type EntityResponseType = HttpResponse<ITour>;
type EntityArrayResponseType = HttpResponse<ITour[]>;

@Injectable({ providedIn: 'root' })
export class TourService {
  public resourceUrl = SERVER_API_URL + 'api/tours';

  constructor(protected http: HttpClient) {}

  create(tour: ITour): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tour);
    return this.http
      .post<ITour>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tour: ITour): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tour);
    return this.http
      .put<ITour>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITour>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITour[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tour: ITour): ITour {
    const copy: ITour = Object.assign({}, tour, {
      lockdelay: tour.lockdelay && tour.lockdelay.isValid() ? tour.lockdelay.toJSON() : undefined,
      createdat: tour.createdat && tour.createdat.isValid() ? tour.createdat.toJSON() : undefined,
      updatedat: tour.updatedat && tour.updatedat.isValid() ? tour.updatedat.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.lockdelay = res.body.lockdelay ? moment(res.body.lockdelay) : undefined;
      res.body.createdat = res.body.createdat ? moment(res.body.createdat) : undefined;
      res.body.updatedat = res.body.updatedat ? moment(res.body.updatedat) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tour: ITour) => {
        tour.lockdelay = tour.lockdelay ? moment(tour.lockdelay) : undefined;
        tour.createdat = tour.createdat ? moment(tour.createdat) : undefined;
        tour.updatedat = tour.updatedat ? moment(tour.updatedat) : undefined;
      });
    }
    return res;
  }
}
