import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBookingproducts } from 'app/shared/model/bookingproducts.model';

type EntityResponseType = HttpResponse<IBookingproducts>;
type EntityArrayResponseType = HttpResponse<IBookingproducts[]>;

@Injectable({ providedIn: 'root' })
export class BookingproductsService {
  public resourceUrl = SERVER_API_URL + 'api/bookingproducts';

  constructor(protected http: HttpClient) {}

  create(bookingproducts: IBookingproducts): Observable<EntityResponseType> {
    return this.http.post<IBookingproducts>(this.resourceUrl, bookingproducts, { observe: 'response' });
  }

  update(bookingproducts: IBookingproducts): Observable<EntityResponseType> {
    return this.http.put<IBookingproducts>(this.resourceUrl, bookingproducts, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBookingproducts>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBookingproducts[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
