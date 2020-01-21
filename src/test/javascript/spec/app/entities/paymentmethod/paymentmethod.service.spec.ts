import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { PaymentmethodService } from 'app/entities/paymentmethod/paymentmethod.service';
import { IPaymentmethod, Paymentmethod } from 'app/shared/model/paymentmethod.model';

describe('Service Tests', () => {
  describe('Paymentmethod Service', () => {
    let injector: TestBed;
    let service: PaymentmethodService;
    let httpMock: HttpTestingController;
    let elemDefault: IPaymentmethod;
    let expectedResult: IPaymentmethod | IPaymentmethod[] | boolean | null;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(PaymentmethodService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Paymentmethod(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'image/png', 'AAAAAAA', currentDate, currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            createdat: currentDate.format(DATE_TIME_FORMAT),
            updatedat: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Paymentmethod', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            createdat: currentDate.format(DATE_TIME_FORMAT),
            updatedat: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            createdat: currentDate,
            updatedat: currentDate
          },
          returnedFromService
        );
        service
          .create(new Paymentmethod())
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Paymentmethod', () => {
        const returnedFromService = Object.assign(
          {
            slug: 'BBBBBB',
            name: 'BBBBBB',
            logo: 'BBBBBB',
            logodata: 'BBBBBB',
            createdat: currentDate.format(DATE_TIME_FORMAT),
            updatedat: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdat: currentDate,
            updatedat: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Paymentmethod', () => {
        const returnedFromService = Object.assign(
          {
            slug: 'BBBBBB',
            name: 'BBBBBB',
            logo: 'BBBBBB',
            logodata: 'BBBBBB',
            createdat: currentDate.format(DATE_TIME_FORMAT),
            updatedat: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            createdat: currentDate,
            updatedat: currentDate
          },
          returnedFromService
        );
        service
          .query()
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Paymentmethod', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
