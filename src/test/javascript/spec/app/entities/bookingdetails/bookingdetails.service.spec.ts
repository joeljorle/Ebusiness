import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { BookingdetailsService } from 'app/entities/bookingdetails/bookingdetails.service';
import { IBookingdetails, Bookingdetails } from 'app/shared/model/bookingdetails.model';
import { Shippingstate } from 'app/shared/model/enumerations/shippingstate.model';
import { Evenementstate } from 'app/shared/model/enumerations/evenementstate.model';

describe('Service Tests', () => {
  describe('Bookingdetails Service', () => {
    let injector: TestBed;
    let service: BookingdetailsService;
    let httpMock: HttpTestingController;
    let elemDefault: IBookingdetails;
    let expectedResult: IBookingdetails | IBookingdetails[] | boolean | null;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(BookingdetailsService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Bookingdetails(
        0,
        0,
        0,
        0,
        0,
        Shippingstate.COMPLETED,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        Evenementstate.COMPLETED,
        0
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            startdate: currentDate.format(DATE_TIME_FORMAT),
            enddate: currentDate.format(DATE_TIME_FORMAT)
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

      it('should create a Bookingdetails', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            startdate: currentDate.format(DATE_TIME_FORMAT),
            enddate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            startdate: currentDate,
            enddate: currentDate
          },
          returnedFromService
        );
        service
          .create(new Bookingdetails())
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Bookingdetails', () => {
        const returnedFromService = Object.assign(
          {
            subtotal: 1,
            total: 1,
            tax: 1,
            shipping: 1,
            shippingstate: 'BBBBBB',
            about: 'BBBBBB',
            couponconde: 'BBBBBB',
            qrcode: 'BBBBBB',
            code: 'BBBBBB',
            url: 'BBBBBB',
            token: 'BBBBBB',
            paymentmode: 'BBBBBB',
            startdate: currentDate.format(DATE_TIME_FORMAT),
            enddate: currentDate.format(DATE_TIME_FORMAT),
            enventstate: 'BBBBBB',
            places: 1
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            startdate: currentDate,
            enddate: currentDate
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

      it('should return a list of Bookingdetails', () => {
        const returnedFromService = Object.assign(
          {
            subtotal: 1,
            total: 1,
            tax: 1,
            shipping: 1,
            shippingstate: 'BBBBBB',
            about: 'BBBBBB',
            couponconde: 'BBBBBB',
            qrcode: 'BBBBBB',
            code: 'BBBBBB',
            url: 'BBBBBB',
            token: 'BBBBBB',
            paymentmode: 'BBBBBB',
            startdate: currentDate.format(DATE_TIME_FORMAT),
            enddate: currentDate.format(DATE_TIME_FORMAT),
            enventstate: 'BBBBBB',
            places: 1
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            startdate: currentDate,
            enddate: currentDate
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

      it('should delete a Bookingdetails', () => {
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
