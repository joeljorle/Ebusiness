import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFollows } from 'app/shared/model/follows.model';

type EntityResponseType = HttpResponse<IFollows>;
type EntityArrayResponseType = HttpResponse<IFollows[]>;

@Injectable({ providedIn: 'root' })
export class FollowsService {
  public resourceUrl = SERVER_API_URL + 'api/follows';

  constructor(protected http: HttpClient) {}

  create(follows: IFollows): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(follows);
    return this.http
      .post<IFollows>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(follows: IFollows): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(follows);
    return this.http
      .put<IFollows>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IFollows>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFollows[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(follows: IFollows): IFollows {
    const copy: IFollows = Object.assign({}, follows, {
      createdat: follows.createdat && follows.createdat.isValid() ? follows.createdat.toJSON() : undefined,
      updatedat: follows.updatedat && follows.updatedat.isValid() ? follows.updatedat.toJSON() : undefined
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
      res.body.forEach((follows: IFollows) => {
        follows.createdat = follows.createdat ? moment(follows.createdat) : undefined;
        follows.updatedat = follows.updatedat ? moment(follows.updatedat) : undefined;
      });
    }
    return res;
  }
}
