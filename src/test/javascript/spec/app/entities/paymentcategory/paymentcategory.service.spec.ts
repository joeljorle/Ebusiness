import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { PaymentcategoryService } from 'app/entities/paymentcategory/paymentcategory.service';
import { IPaymentcategory, Paymentcategory } from 'app/shared/model/paymentcategory.model';

describe('Service Tests', () => {
  describe('Paymentcategory Service', () => {
    let injector: TestBed;
    let service: PaymentcategoryService;
    let httpMock: HttpTestingController;
    let elemDefault: IPaymentcategory;
    let expectedResult: IPaymentcategory | IPaymentcategory[] | boolean | null;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(PaymentcategoryService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Paymentcategory(
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate
      );
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

      it('should create a Paymentcategory', () => {
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
          .create(new Paymentcategory())
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Paymentcategory', () => {
        const returnedFromService = Object.assign(
          {
            paymentmethodid: 1,
            categoryid: 1,
            url: 'BBBBBB',
            apiurl: 'BBBBBB',
            apikey: 'BBBBBB',
            key2: 'BBBBBB',
            key3: 'BBBBBB',
            key4: 'BBBBBB',
            phonenumber: 'BBBBBB',
            chanel: 'BBBBBB',
            code: 'BBBBBB',
            username: 'BBBBBB',
            password: 'BBBBBB',
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

      it('should return a list of Paymentcategory', () => {
        const returnedFromService = Object.assign(
          {
            paymentmethodid: 1,
            categoryid: 1,
            url: 'BBBBBB',
            apiurl: 'BBBBBB',
            apikey: 'BBBBBB',
            key2: 'BBBBBB',
            key3: 'BBBBBB',
            key4: 'BBBBBB',
            phonenumber: 'BBBBBB',
            chanel: 'BBBBBB',
            code: 'BBBBBB',
            username: 'BBBBBB',
            password: 'BBBBBB',
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

      it('should delete a Paymentcategory', () => {
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
