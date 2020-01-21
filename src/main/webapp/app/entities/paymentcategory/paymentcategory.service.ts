import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPaymentcategory } from 'app/shared/model/paymentcategory.model';

type EntityResponseType = HttpResponse<IPaymentcategory>;
type EntityArrayResponseType = HttpResponse<IPaymentcategory[]>;

@Injectable({ providedIn: 'root' })
export class PaymentcategoryService {
  public resourceUrl = SERVER_API_URL + 'api/paymentcategories';

  constructor(protected http: HttpClient) {}

  create(paymentcategory: IPaymentcategory): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(paymentcategory);
    return this.http
      .post<IPaymentcategory>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(paymentcategory: IPaymentcategory): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(paymentcategory);
    return this.http
      .put<IPaymentcategory>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPaymentcategory>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPaymentcategory[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(paymentcategory: IPaymentcategory): IPaymentcategory {
    const copy: IPaymentcategory = Object.assign({}, paymentcategory, {
      createdat: paymentcategory.createdat && paymentcategory.createdat.isValid() ? paymentcategory.createdat.toJSON() : undefined,
      updatedat: paymentcategory.updatedat && paymentcategory.updatedat.isValid() ? paymentcategory.updatedat.toJSON() : undefined
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
      res.body.forEach((paymentcategory: IPaymentcategory) => {
        paymentcategory.createdat = paymentcategory.createdat ? moment(paymentcategory.createdat) : undefined;
        paymentcategory.updatedat = paymentcategory.updatedat ? moment(paymentcategory.updatedat) : undefined;
      });
    }
    return res;
  }
}
