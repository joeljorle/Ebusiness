import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICurrency } from 'app/shared/model/currency.model';

type EntityResponseType = HttpResponse<ICurrency>;
type EntityArrayResponseType = HttpResponse<ICurrency[]>;

@Injectable({ providedIn: 'root' })
export class CurrencyService {
  public resourceUrl = SERVER_API_URL + 'api/currencies';

  constructor(protected http: HttpClient) {}

  create(currency: ICurrency): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(currency);
    return this.http
      .post<ICurrency>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(currency: ICurrency): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(currency);
    return this.http
      .put<ICurrency>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICurrency>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICurrency[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(currency: ICurrency): ICurrency {
    const copy: ICurrency = Object.assign({}, currency, {
      createdat: currency.createdat && currency.createdat.isValid() ? currency.createdat.toJSON() : undefined,
      updatedat: currency.updatedat && currency.updatedat.isValid() ? currency.updatedat.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdat = res.body.createdat ? moment(res.body.createdat) : undefined;
      res.body.updatedat = res.body.updatedat ? moment(res.body.updatedat) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((currency: ICurrency) => {
        currency.createdat = currency.createdat ? moment(currency.createdat) : undefined;
        currency.updatedat = currency.updatedat ? moment(currency.updatedat) : undefined;
      });
    }
    return res;
  }
}
