import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IShipping } from 'app/shared/model/shipping.model';

type EntityResponseType = HttpResponse<IShipping>;
type EntityArrayResponseType = HttpResponse<IShipping[]>;

@Injectable({ providedIn: 'root' })
export class ShippingService {
  public resourceUrl = SERVER_API_URL + 'api/shippings';

  constructor(protected http: HttpClient) {}

  create(shipping: IShipping): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(shipping);
    return this.http
      .post<IShipping>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(shipping: IShipping): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(shipping);
    return this.http
      .put<IShipping>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IShipping>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IShipping[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(shipping: IShipping): IShipping {
    const copy: IShipping = Object.assign({}, shipping, {
      createdat: shipping.createdat && shipping.createdat.isValid() ? shipping.createdat.toJSON() : undefined,
      updatedat: shipping.updatedat && shipping.updatedat.isValid() ? shipping.updatedat.toJSON() : undefined
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
      res.body.forEach((shipping: IShipping) => {
        shipping.createdat = shipping.createdat ? moment(shipping.createdat) : undefined;
        shipping.updatedat = shipping.updatedat ? moment(shipping.updatedat) : undefined;
      });
    }
    return res;
  }
}
