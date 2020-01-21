import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { EvenementService } from 'app/entities/evenement/evenement.service';
import { IEvenement, Evenement } from 'app/shared/model/evenement.model';
import { Tagcolor } from 'app/shared/model/enumerations/tagcolor.model';

describe('Service Tests', () => {
  describe('Evenement Service', () => {
    let injector: TestBed;
    let service: EvenementService;
    let httpMock: HttpTestingController;
    let elemDefault: IEvenement;
    let expectedResult: IEvenement | IEvenement[] | boolean | null;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(EvenementService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Evenement(
        0,
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        false,
        currentDate,
        false,
        currentDate,
        0,
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        Tagcolor.RED,
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
        'AAAAAAA',
        currentDate,
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            lockdelay: currentDate.format(DATE_TIME_FORMAT),
            limiteddelay: currentDate.format(DATE_TIME_FORMAT),
            startdate: currentDate.format(DATE_TIME_FORMAT),
            enddate: currentDate.format(DATE_TIME_FORMAT),
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

      it('should create a Evenement', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            lockdelay: currentDate.format(DATE_TIME_FORMAT),
            limiteddelay: currentDate.format(DATE_TIME_FORMAT),
            startdate: currentDate.format(DATE_TIME_FORMAT),
            enddate: currentDate.format(DATE_TIME_FORMAT),
            createdat: currentDate.format(DATE_TIME_FORMAT),
            updatedat: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            lockdelay: currentDate,
            limiteddelay: currentDate,
            startdate: currentDate,
            enddate: currentDate,
            createdat: currentDate,
            updatedat: currentDate
          },
          returnedFromService
        );
        service
          .create(new Evenement())
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Evenement', () => {
        const returnedFromService = Object.assign(
          {
            categoryid: 1,
            userid: 1,
            productid: 1,
            slug: 'BBBBBB',
            name: 'BBBBBB',
            islock: true,
            lockdelay: currentDate.format(DATE_TIME_FORMAT),
            islimited: true,
            limiteddelay: currentDate.format(DATE_TIME_FORMAT),
            limitedbooking: 1,
            startdate: currentDate.format(DATE_TIME_FORMAT),
            enddate: currentDate.format(DATE_TIME_FORMAT),
            about: 'BBBBBB',
            title: 'BBBBBB',
            tag: 'BBBBBB',
            tagcolor: 'BBBBBB',
            postalcode: 'BBBBBB',
            phones: 'BBBBBB',
            website: 'BBBBBB',
            facebook: 'BBBBBB',
            twitter: 'BBBBBB',
            gplus: 'BBBBBB',
            linkedin: 'BBBBBB',
            instagram: 'BBBBBB',
            email: 'BBBBBB',
            url: 'BBBBBB',
            othercontacts: 'BBBBBB',
            otherfields: 'BBBBBB',
            createdat: currentDate.format(DATE_TIME_FORMAT),
            updatedat: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            lockdelay: currentDate,
            limiteddelay: currentDate,
            startdate: currentDate,
            enddate: currentDate,
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

      it('should return a list of Evenement', () => {
        const returnedFromService = Object.assign(
          {
            categoryid: 1,
            userid: 1,
            productid: 1,
            slug: 'BBBBBB',
            name: 'BBBBBB',
            islock: true,
            lockdelay: currentDate.format(DATE_TIME_FORMAT),
            islimited: true,
            limiteddelay: currentDate.format(DATE_TIME_FORMAT),
            limitedbooking: 1,
            startdate: currentDate.format(DATE_TIME_FORMAT),
            enddate: currentDate.format(DATE_TIME_FORMAT),
            about: 'BBBBBB',
            title: 'BBBBBB',
            tag: 'BBBBBB',
            tagcolor: 'BBBBBB',
            postalcode: 'BBBBBB',
            phones: 'BBBBBB',
            website: 'BBBBBB',
            facebook: 'BBBBBB',
            twitter: 'BBBBBB',
            gplus: 'BBBBBB',
            linkedin: 'BBBBBB',
            instagram: 'BBBBBB',
            email: 'BBBBBB',
            url: 'BBBBBB',
            othercontacts: 'BBBBBB',
            otherfields: 'BBBBBB',
            createdat: currentDate.format(DATE_TIME_FORMAT),
            updatedat: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            lockdelay: currentDate,
            limiteddelay: currentDate,
            startdate: currentDate,
            enddate: currentDate,
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

      it('should delete a Evenement', () => {
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
