import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPaymentmethod } from 'app/shared/model/paymentmethod.model';

type EntityResponseType = HttpResponse<IPaymentmethod>;
type EntityArrayResponseType = HttpResponse<IPaymentmethod[]>;

@Injectable({ providedIn: 'root' })
export class PaymentmethodService {
  public resourceUrl = SERVER_API_URL + 'api/paymentmethods';

  constructor(protected http: HttpClient) {}

  create(paymentmethod: IPaymentmethod): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(paymentmethod);
    return this.http
      .post<IPaymentmethod>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(paymentmethod: IPaymentmethod): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(paymentmethod);
    return this.http
      .put<IPaymentmethod>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPaymentmethod>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPaymentmethod[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(paymentmethod: IPaymentmethod): IPaymentmethod {
    const copy: IPaymentmethod = Object.assign({}, paymentmethod, {
      createdat: paymentmethod.createdat && paymentmethod.createdat.isValid() ? paymentmethod.createdat.toJSON() : undefined,
      updatedat: paymentmethod.updatedat && paymentmethod.updatedat.isValid() ? paymentmethod.updatedat.toJSON() : undefined
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
      res.body.forEach((paymentmethod: IPaymentmethod) => {
        paymentmethod.createdat = paymentmethod.createdat ? moment(paymentmethod.createdat) : undefined;
        paymentmethod.updatedat = paymentmethod.updatedat ? moment(paymentmethod.updatedat) : undefined;
      });
    }
    return res;
  }
}
