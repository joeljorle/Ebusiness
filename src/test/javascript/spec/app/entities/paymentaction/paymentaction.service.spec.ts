import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { PaymentactionService } from 'app/entities/paymentaction/paymentaction.service';
import { IPaymentaction, Paymentaction } from 'app/shared/model/paymentaction.model';

describe('Service Tests', () => {
  describe('Paymentaction Service', () => {
    let injector: TestBed;
    let service: PaymentactionService;
    let httpMock: HttpTestingController;
    let elemDefault: IPaymentaction;
    let expectedResult: IPaymentaction | IPaymentaction[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(PaymentactionService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Paymentaction(
        0,
        'AAAAAAA',
        0,
        0,
        0,
        0,
        0,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        currentDate,
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            expireat: currentDate.format(DATE_TIME_FORMAT),
            createdat: currentDate.format(DATE_TIME_FORMAT),
            updatedat: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Paymentaction', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            expireat: currentDate.format(DATE_TIME_FORMAT),
            createdat: currentDate.format(DATE_TIME_FORMAT),
            updatedat: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            expireat: currentDate,
            createdat: currentDate,
            updatedat: currentDate
          },
          returnedFromService
        );

        service.create(new Paymentaction()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Paymentaction', () => {
        const returnedFromService = Object.assign(
          {
            slug: 'BBBBBB',
            userid: 1,
            paymentmethodid: 1,
            evenementid: 1,
            productid: 1,
            categoryid: 1,
            expireat: currentDate.format(DATE_TIME_FORMAT),
            code: 'BBBBBB',
            code1: 'BBBBBB',
            code2: 'BBBBBB',
            amount: 1,
            createdat: currentDate.format(DATE_TIME_FORMAT),
            updatedat: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            expireat: currentDate,
            createdat: currentDate,
            updatedat: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Paymentaction', () => {
        const returnedFromService = Object.assign(
          {
            slug: 'BBBBBB',
            userid: 1,
            paymentmethodid: 1,
            evenementid: 1,
            productid: 1,
            categoryid: 1,
            expireat: currentDate.format(DATE_TIME_FORMAT),
            code: 'BBBBBB',
            code1: 'BBBBBB',
            code2: 'BBBBBB',
            amount: 1,
            createdat: currentDate.format(DATE_TIME_FORMAT),
            updatedat: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            expireat: currentDate,
            createdat: currentDate,
            updatedat: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Paymentaction', () => {
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
