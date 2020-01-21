import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IActus } from 'app/shared/model/actus.model';

type EntityResponseType = HttpResponse<IActus>;
type EntityArrayResponseType = HttpResponse<IActus[]>;

@Injectable({ providedIn: 'root' })
export class ActusService {
  public resourceUrl = SERVER_API_URL + 'api/actuses';

  constructor(protected http: HttpClient) {}

  create(actus: IActus): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(actus);
    return this.http
      .post<IActus>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(actus: IActus): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(actus);
    return this.http
      .put<IActus>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IActus>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IActus[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(actus: IActus): IActus {
    const copy: IActus = Object.assign({}, actus, {
      createdat: actus.createdat && actus.createdat.isValid() ? actus.createdat.toJSON() : undefined,
      updatedat: actus.updatedat && actus.updatedat.isValid() ? actus.updatedat.toJSON() : undefined
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
      res.body.forEach((actus: IActus) => {
        actus.createdat = actus.createdat ? moment(actus.createdat) : undefined;
        actus.updatedat = actus.updatedat ? moment(actus.updatedat) : undefined;
      });
    }
    return res;
  }
}
