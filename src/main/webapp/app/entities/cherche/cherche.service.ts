import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICherche } from 'app/shared/model/cherche.model';

type EntityResponseType = HttpResponse<ICherche>;
type EntityArrayResponseType = HttpResponse<ICherche[]>;

@Injectable({ providedIn: 'root' })
export class ChercheService {
  public resourceUrl = SERVER_API_URL + 'api/cherches';

  constructor(protected http: HttpClient) {}

  create(cherche: ICherche): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(cherche);
    return this.http
      .post<ICherche>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(cherche: ICherche): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(cherche);
    return this.http
      .put<ICherche>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICherche>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICherche[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(cherche: ICherche): ICherche {
    const copy: ICherche = Object.assign({}, cherche, {
      createdat: cherche.createdat && cherche.createdat.isValid() ? cherche.createdat.toJSON() : undefined,
      updatedat: cherche.updatedat && cherche.updatedat.isValid() ? cherche.updatedat.toJSON() : undefined
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
      res.body.forEach((cherche: ICherche) => {
        cherche.createdat = cherche.createdat ? moment(cherche.createdat) : undefined;
        cherche.updatedat = cherche.updatedat ? moment(cherche.updatedat) : undefined;
      });
    }
    return res;
  }
}
