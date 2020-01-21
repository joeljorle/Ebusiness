import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICategorydetails } from 'app/shared/model/categorydetails.model';

type EntityResponseType = HttpResponse<ICategorydetails>;
type EntityArrayResponseType = HttpResponse<ICategorydetails[]>;

@Injectable({ providedIn: 'root' })
export class CategorydetailsService {
  public resourceUrl = SERVER_API_URL + 'api/categorydetails';

  constructor(protected http: HttpClient) {}

  create(categorydetails: ICategorydetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(categorydetails);
    return this.http
      .post<ICategorydetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(categorydetails: ICategorydetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(categorydetails);
    return this.http
      .put<ICategorydetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICategorydetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICategorydetails[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(categorydetails: ICategorydetails): ICategorydetails {
    const copy: ICategorydetails = Object.assign({}, categorydetails, {
      generalhiddendelay:
        categorydetails.generalhiddendelay && categorydetails.generalhiddendelay.isValid()
          ? categorydetails.generalhiddendelay.toJSON()
          : undefined,
      generallockdelay:
        categorydetails.generallockdelay && categorydetails.generallockdelay.isValid()
          ? categorydetails.generallockdelay.toJSON()
          : undefined,
      generalexpiredelay:
        categorydetails.generalexpiredelay && categorydetails.generalexpiredelay.isValid()
          ? categorydetails.generalexpiredelay.toJSON()
          : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.generalhiddendelay = res.body.generalhiddendelay ? moment(res.body.generalhiddendelay) : undefined;
      res.body.generallockdelay = res.body.generallockdelay ? moment(res.body.generallockdelay) : undefined;
      res.body.generalexpiredelay = res.body.generalexpiredelay ? moment(res.body.generalexpiredelay) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((categorydetails: ICategorydetails) => {
        categorydetails.generalhiddendelay = categorydetails.generalhiddendelay ? moment(categorydetails.generalhiddendelay) : undefined;
        categorydetails.generallockdelay = categorydetails.generallockdelay ? moment(categorydetails.generallockdelay) : undefined;
        categorydetails.generalexpiredelay = categorydetails.generalexpiredelay ? moment(categorydetails.generalexpiredelay) : undefined;
      });
    }
    return res;
  }
}
