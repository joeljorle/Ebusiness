import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { CurrencyService } from 'app/entities/currency/currency.service';
import { ICurrency, Currency } from 'app/shared/model/currency.model';

describe('Service Tests', () => {
  describe('Currency Service', () => {
    let injector: TestBed;
    let service: CurrencyService;
    let httpMock: HttpTestingController;
    let elemDefault: ICurrency;
    let expectedResult: ICurrency | ICurrency[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CurrencyService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Currency(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'image/png', 'AAAAAAA', currentDate, currentDate);
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

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Currency', () => {
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

        service.create(new Currency()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Currency', () => {
        const returnedFromService = Object.assign(
          {
            slug: 'BBBBBB',
            name: 'BBBBBB',
            abrev: 'BBBBBB',
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

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Currency', () => {
        const returnedFromService = Object.assign(
          {
            slug: 'BBBBBB',
            name: 'BBBBBB',
            abrev: 'BBBBBB',
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

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Currency', () => {
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
