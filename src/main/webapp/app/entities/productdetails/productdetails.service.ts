import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProductdetails } from 'app/shared/model/productdetails.model';

type EntityResponseType = HttpResponse<IProductdetails>;
type EntityArrayResponseType = HttpResponse<IProductdetails[]>;

@Injectable({ providedIn: 'root' })
export class ProductdetailsService {
  public resourceUrl = SERVER_API_URL + 'api/productdetails';

  constructor(protected http: HttpClient) {}

  create(productdetails: IProductdetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(productdetails);
    return this.http
      .post<IProductdetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(productdetails: IProductdetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(productdetails);
    return this.http
      .put<IProductdetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IProductdetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IProductdetails[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(productdetails: IProductdetails): IProductdetails {
    const copy: IProductdetails = Object.assign({}, productdetails, {
      availableat: productdetails.availableat && productdetails.availableat.isValid() ? productdetails.availableat.toJSON() : undefined,
      lockactiondelay:
        productdetails.lockactiondelay && productdetails.lockactiondelay.isValid() ? productdetails.lockactiondelay.toJSON() : undefined,
      expireat: productdetails.expireat && productdetails.expireat.isValid() ? productdetails.expireat.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.availableat = res.body.availableat ? moment(res.body.availableat) : undefined;
      res.body.lockactiondelay = res.body.lockactiondelay ? moment(res.body.lockactiondelay) : undefined;
      res.body.expireat = res.body.expireat ? moment(res.body.expireat) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((productdetails: IProductdetails) => {
        productdetails.availableat = productdetails.availableat ? moment(productdetails.availableat) : undefined;
        productdetails.lockactiondelay = productdetails.lockactiondelay ? moment(productdetails.lockactiondelay) : undefined;
        productdetails.expireat = productdetails.expireat ? moment(productdetails.expireat) : undefined;
      });
    }
    return res;
  }
}
