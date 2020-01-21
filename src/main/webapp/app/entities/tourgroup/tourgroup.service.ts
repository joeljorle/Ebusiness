import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITourgroup } from 'app/shared/model/tourgroup.model';

type EntityResponseType = HttpResponse<ITourgroup>;
type EntityArrayResponseType = HttpResponse<ITourgroup[]>;

@Injectable({ providedIn: 'root' })
export class TourgroupService {
  public resourceUrl = SERVER_API_URL + 'api/tourgroups';

  constructor(protected http: HttpClient) {}

  create(tourgroup: ITourgroup): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tourgroup);
    return this.http
      .post<ITourgroup>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tourgroup: ITourgroup): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tourgroup);
    return this.http
      .put<ITourgroup>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITourgroup>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITourgroup[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tourgroup: ITourgroup): ITourgroup {
    const copy: ITourgroup = Object.assign({}, tourgroup, {
      lockdelay: tourgroup.lockdelay && tourgroup.lockdelay.isValid() ? tourgroup.lockdelay.toJSON() : undefined,
      createdat: tourgroup.createdat && tourgroup.createdat.isValid() ? tourgroup.createdat.toJSON() : undefined,
      updatedat: tourgroup.updatedat && tourgroup.updatedat.isValid() ? tourgroup.updatedat.toJSON() : undefined
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
      res.body.forEach((tourgroup: ITourgroup) => {
        tourgroup.lockdelay = tourgroup.lockdelay ? moment(tourgroup.lockdelay) : undefined;
        tourgroup.createdat = tourgroup.createdat ? moment(tourgroup.createdat) : undefined;
        tourgroup.updatedat = tourgroup.updatedat ? moment(tourgroup.updatedat) : undefined;
      });
    }
    return res;
  }
}
