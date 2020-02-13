import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBookingview } from 'app/shared/model/bookingview.model';

type EntityResponseType = HttpResponse<IBookingview>;
type EntityArrayResponseType = HttpResponse<IBookingview[]>;

@Injectable({ providedIn: 'root' })
export class BookingviewService {
  public resourceUrl = SERVER_API_URL + 'api/bookingviews';

  constructor(protected http: HttpClient) {}

  create(bookingview: IBookingview): Observable<EntityResponseType> {
    return this.http.post<IBookingview>(this.resourceUrl, bookingview, { observe: 'response' });
  }

  update(bookingview: IBookingview): Observable<EntityResponseType> {
    return this.http.put<IBookingview>(this.resourceUrl, bookingview, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBookingview>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBookingview[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
