import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITourcategory } from 'app/shared/model/tourcategory.model';

type EntityResponseType = HttpResponse<ITourcategory>;
type EntityArrayResponseType = HttpResponse<ITourcategory[]>;

@Injectable({ providedIn: 'root' })
export class TourcategoryService {
  public resourceUrl = SERVER_API_URL + 'api/tourcategories';

  constructor(protected http: HttpClient) {}

  create(tourcategory: ITourcategory): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tourcategory);
    return this.http
      .post<ITourcategory>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tourcategory: ITourcategory): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tourcategory);
    return this.http
      .put<ITourcategory>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITourcategory>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITourcategory[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tourcategory: ITourcategory): ITourcategory {
    const copy: ITourcategory = Object.assign({}, tourcategory, {
      lockdelay: tourcategory.lockdelay && tourcategory.lockdelay.isValid() ? tourcategory.lockdelay.toJSON() : undefined,
      createdat: tourcategory.createdat && tourcategory.createdat.isValid() ? tourcategory.createdat.toJSON() : undefined,
      updatedat: tourcategory.updatedat && tourcategory.updatedat.isValid() ? tourcategory.updatedat.toJSON() : undefined
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
      res.body.forEach((tourcategory: ITourcategory) => {
        tourcategory.lockdelay = tourcategory.lockdelay ? moment(tourcategory.lockdelay) : undefined;
        tourcategory.createdat = tourcategory.createdat ? moment(tourcategory.createdat) : undefined;
        tourcategory.updatedat = tourcategory.updatedat ? moment(tourcategory.updatedat) : undefined;
      });
    }
    return res;
  }
}
