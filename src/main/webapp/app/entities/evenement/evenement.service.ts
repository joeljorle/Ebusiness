import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEvenement } from 'app/shared/model/evenement.model';

type EntityResponseType = HttpResponse<IEvenement>;
type EntityArrayResponseType = HttpResponse<IEvenement[]>;

@Injectable({ providedIn: 'root' })
export class EvenementService {
  public resourceUrl = SERVER_API_URL + 'api/evenements';

  constructor(protected http: HttpClient) {}

  create(evenement: IEvenement): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(evenement);
    return this.http
      .post<IEvenement>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(evenement: IEvenement): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(evenement);
    return this.http
      .put<IEvenement>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IEvenement>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IEvenement[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(evenement: IEvenement): IEvenement {
    const copy: IEvenement = Object.assign({}, evenement, {
      lockdelay: evenement.lockdelay && evenement.lockdelay.isValid() ? evenement.lockdelay.toJSON() : undefined,
      limiteddelay: evenement.limiteddelay && evenement.limiteddelay.isValid() ? evenement.limiteddelay.toJSON() : undefined,
      startdate: evenement.startdate && evenement.startdate.isValid() ? evenement.startdate.toJSON() : undefined,
      enddate: evenement.enddate && evenement.enddate.isValid() ? evenement.enddate.toJSON() : undefined,
      createdat: evenement.createdat && evenement.createdat.isValid() ? evenement.createdat.toJSON() : undefined,
      updatedat: evenement.updatedat && evenement.updatedat.isValid() ? evenement.updatedat.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.lockdelay = res.body.lockdelay ? moment(res.body.lockdelay) : undefined;
      res.body.limiteddelay = res.body.limiteddelay ? moment(res.body.limiteddelay) : undefined;
      res.body.startdate = res.body.startdate ? moment(res.body.startdate) : undefined;
      res.body.enddate = res.body.enddate ? moment(res.body.enddate) : undefined;
      res.body.createdat = res.body.createdat ? moment(res.body.createdat) : undefined;
      res.body.updatedat = res.body.updatedat ? moment(res.body.updatedat) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((evenement: IEvenement) => {
        evenement.lockdelay = evenement.lockdelay ? moment(evenement.lockdelay) : undefined;
        evenement.limiteddelay = evenement.limiteddelay ? moment(evenement.limiteddelay) : undefined;
        evenement.startdate = evenement.startdate ? moment(evenement.startdate) : undefined;
        evenement.enddate = evenement.enddate ? moment(evenement.enddate) : undefined;
        evenement.createdat = evenement.createdat ? moment(evenement.createdat) : undefined;
        evenement.updatedat = evenement.updatedat ? moment(evenement.updatedat) : undefined;
      });
    }
    return res;
  }
}
