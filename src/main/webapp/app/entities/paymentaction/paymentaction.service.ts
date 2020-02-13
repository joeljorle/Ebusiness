import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPaymentaction } from 'app/shared/model/paymentaction.model';

type EntityResponseType = HttpResponse<IPaymentaction>;
type EntityArrayResponseType = HttpResponse<IPaymentaction[]>;

@Injectable({ providedIn: 'root' })
export class PaymentactionService {
  public resourceUrl = SERVER_API_URL + 'api/paymentactions';

  constructor(protected http: HttpClient) {}

  create(paymentaction: IPaymentaction): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(paymentaction);
    return this.http
      .post<IPaymentaction>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(paymentaction: IPaymentaction): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(paymentaction);
    return this.http
      .put<IPaymentaction>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPaymentaction>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPaymentaction[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(paymentaction: IPaymentaction): IPaymentaction {
    const copy: IPaymentaction = Object.assign({}, paymentaction, {
      expireat: paymentaction.expireat && paymentaction.expireat.isValid() ? paymentaction.expireat.toJSON() : undefined,
      createdat: paymentaction.createdat && paymentaction.createdat.isValid() ? paymentaction.createdat.toJSON() : undefined,
      updatedat: paymentaction.updatedat && paymentaction.updatedat.isValid() ? paymentaction.updatedat.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.expireat = res.body.expireat ? moment(res.body.expireat) : undefined;
      res.body.createdat = res.body.createdat ? moment(res.body.createdat) : undefined;
      res.body.updatedat = res.body.updatedat ? moment(res.body.updatedat) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((paymentaction: IPaymentaction) => {
        paymentaction.expireat = paymentaction.expireat ? moment(paymentaction.expireat) : undefined;
        paymentaction.createdat = paymentaction.createdat ? moment(paymentaction.createdat) : undefined;
        paymentaction.updatedat = paymentaction.updatedat ? moment(paymentaction.updatedat) : undefined;
      });
    }
    return res;
  }
}
