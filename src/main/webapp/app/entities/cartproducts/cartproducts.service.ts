import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICartproducts } from 'app/shared/model/cartproducts.model';

type EntityResponseType = HttpResponse<ICartproducts>;
type EntityArrayResponseType = HttpResponse<ICartproducts[]>;

@Injectable({ providedIn: 'root' })
export class CartproductsService {
  public resourceUrl = SERVER_API_URL + 'api/cartproducts';

  constructor(protected http: HttpClient) {}

  create(cartproducts: ICartproducts): Observable<EntityResponseType> {
    return this.http.post<ICartproducts>(this.resourceUrl, cartproducts, { observe: 'response' });
  }

  update(cartproducts: ICartproducts): Observable<EntityResponseType> {
    return this.http.put<ICartproducts>(this.resourceUrl, cartproducts, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICartproducts>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICartproducts[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
