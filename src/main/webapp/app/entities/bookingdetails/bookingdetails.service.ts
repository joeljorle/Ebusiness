import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBookingdetails } from 'app/shared/model/bookingdetails.model';

type EntityResponseType = HttpResponse<IBookingdetails>;
type EntityArrayResponseType = HttpResponse<IBookingdetails[]>;

@Injectable({ providedIn: 'root' })
export class BookingdetailsService {
  public resourceUrl = SERVER_API_URL + 'api/bookingdetails';

  constructor(protected http: HttpClient) {}

  create(bookingdetails: IBookingdetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bookingdetails);
    return this.http
      .post<IBookingdetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(bookingdetails: IBookingdetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bookingdetails);
    return this.http
      .put<IBookingdetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBookingdetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBookingdetails[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(bookingdetails: IBookingdetails): IBookingdetails {
    const copy: IBookingdetails = Object.assign({}, bookingdetails, {
      startdate: bookingdetails.startdate && bookingdetails.startdate.isValid() ? bookingdetails.startdate.toJSON() : undefined,
      enddate: bookingdetails.enddate && bookingdetails.enddate.isValid() ? bookingdetails.enddate.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.startdate = res.body.startdate ? moment(res.body.startdate) : undefined;
      res.body.enddate = res.body.enddate ? moment(res.body.enddate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((bookingdetails: IBookingdetails) => {
        bookingdetails.startdate = bookingdetails.startdate ? moment(bookingdetails.startdate) : undefined;
        bookingdetails.enddate = bookingdetails.enddate ? moment(bookingdetails.enddate) : undefined;
      });
    }
    return res;
  }
}
