import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFiles } from 'app/shared/model/files.model';

type EntityResponseType = HttpResponse<IFiles>;
type EntityArrayResponseType = HttpResponse<IFiles[]>;

@Injectable({ providedIn: 'root' })
export class FilesService {
  public resourceUrl = SERVER_API_URL + 'api/files';

  constructor(protected http: HttpClient) {}

  create(files: IFiles): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(files);
    return this.http
      .post<IFiles>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(files: IFiles): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(files);
    return this.http
      .put<IFiles>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IFiles>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFiles[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(files: IFiles): IFiles {
    const copy: IFiles = Object.assign({}, files, {
      createdat: files.createdat && files.createdat.isValid() ? files.createdat.toJSON() : undefined,
      updatedat: files.updatedat && files.updatedat.isValid() ? files.updatedat.toJSON() : undefined
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
      res.body.forEach((files: IFiles) => {
        files.createdat = files.createdat ? moment(files.createdat) : undefined;
        files.updatedat = files.updatedat ? moment(files.updatedat) : undefined;
      });
    }
    return res;
  }
}
