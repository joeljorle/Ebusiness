import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRatings } from 'app/shared/model/ratings.model';

type EntityResponseType = HttpResponse<IRatings>;
type EntityArrayResponseType = HttpResponse<IRatings[]>;

@Injectable({ providedIn: 'root' })
export class RatingsService {
  public resourceUrl = SERVER_API_URL + 'api/ratings';

  constructor(protected http: HttpClient) {}

  create(ratings: IRatings): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ratings);
    return this.http
      .post<IRatings>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(ratings: IRatings): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ratings);
    return this.http
      .put<IRatings>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IRatings>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IRatings[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(ratings: IRatings): IRatings {
    const copy: IRatings = Object.assign({}, ratings, {
      createdat: ratings.createdat && ratings.createdat.isValid() ? ratings.createdat.toJSON() : undefined,
      updatedat: ratings.updatedat && ratings.updatedat.isValid() ? ratings.updatedat.toJSON() : undefined
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
      res.body.forEach((ratings: IRatings) => {
        ratings.createdat = ratings.createdat ? moment(ratings.createdat) : undefined;
        ratings.updatedat = ratings.updatedat ? moment(ratings.updatedat) : undefined;
      });
    }
    return res;
  }
}
