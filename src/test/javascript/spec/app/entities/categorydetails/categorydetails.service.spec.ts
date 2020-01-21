import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { CategorydetailsService } from 'app/entities/categorydetails/categorydetails.service';
import { ICategorydetails, Categorydetails } from 'app/shared/model/categorydetails.model';
import { Tagcolor } from 'app/shared/model/enumerations/tagcolor.model';

describe('Service Tests', () => {
  describe('Categorydetails Service', () => {
    let injector: TestBed;
    let service: CategorydetailsService;
    let httpMock: HttpTestingController;
    let elemDefault: ICategorydetails;
    let expectedResult: ICategorydetails | ICategorydetails[] | boolean | null;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CategorydetailsService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Categorydetails(
        0,
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
        'AAAAAAA',
        false,
        0,
        0,
        0,
        0,
        'AAAAAAA',
        Tagcolor.RED,
        false,
        currentDate,
        false,
        currentDate,
        false,
        currentDate,
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            generalhiddendelay: currentDate.format(DATE_TIME_FORMAT),
            generallockdelay: currentDate.format(DATE_TIME_FORMAT),
            generalexpiredelay: currentDate.format(DATE_TIME_FORMAT)
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

      it('should create a Categorydetails', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            generalhiddendelay: currentDate.format(DATE_TIME_FORMAT),
            generallockdelay: currentDate.format(DATE_TIME_FORMAT),
            generalexpiredelay: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            generalhiddendelay: currentDate,
            generallockdelay: currentDate,
            generalexpiredelay: currentDate
          },
          returnedFromService
        );
        service
          .create(new Categorydetails())
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Categorydetails', () => {
        const returnedFromService = Object.assign(
          {
            about: 'BBBBBB',
            title: 'BBBBBB',
            tag: 'BBBBBB',
            tagcolor: 'BBBBBB',
            defaultlanguage: 'BBBBBB',
            postalcode: 'BBBBBB',
            phones: 'BBBBBB',
            website: 'BBBBBB',
            email: 'BBBBBB',
            facebook: 'BBBBBB',
            twitter: 'BBBBBB',
            gplus: 'BBBBBB',
            linkedin: 'BBBBBB',
            instagram: 'BBBBBB',
            opentimes: 'BBBBBB',
            othercontacts: 'BBBBBB',
            otherfields: 'BBBBBB',
            generalabout: true,
            generaltax: 1,
            generalstock: 1,
            generalprice: 1,
            generalmaxstock: 1,
            generaltag: 'BBBBBB',
            generaltagcolor: 'BBBBBB',
            generalhidden: true,
            generalhiddendelay: currentDate.format(DATE_TIME_FORMAT),
            generallock: true,
            generallockdelay: currentDate.format(DATE_TIME_FORMAT),
            generalexpire: true,
            generalexpiredelay: currentDate.format(DATE_TIME_FORMAT),
            childsname: 'BBBBBB',
            productsname: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            generalhiddendelay: currentDate,
            generallockdelay: currentDate,
            generalexpiredelay: currentDate
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

      it('should return a list of Categorydetails', () => {
        const returnedFromService = Object.assign(
          {
            about: 'BBBBBB',
            title: 'BBBBBB',
            tag: 'BBBBBB',
            tagcolor: 'BBBBBB',
            defaultlanguage: 'BBBBBB',
            postalcode: 'BBBBBB',
            phones: 'BBBBBB',
            website: 'BBBBBB',
            email: 'BBBBBB',
            facebook: 'BBBBBB',
            twitter: 'BBBBBB',
            gplus: 'BBBBBB',
            linkedin: 'BBBBBB',
            instagram: 'BBBBBB',
            opentimes: 'BBBBBB',
            othercontacts: 'BBBBBB',
            otherfields: 'BBBBBB',
            generalabout: true,
            generaltax: 1,
            generalstock: 1,
            generalprice: 1,
            generalmaxstock: 1,
            generaltag: 'BBBBBB',
            generaltagcolor: 'BBBBBB',
            generalhidden: true,
            generalhiddendelay: currentDate.format(DATE_TIME_FORMAT),
            generallock: true,
            generallockdelay: currentDate.format(DATE_TIME_FORMAT),
            generalexpire: true,
            generalexpiredelay: currentDate.format(DATE_TIME_FORMAT),
            childsname: 'BBBBBB',
            productsname: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            generalhiddendelay: currentDate,
            generallockdelay: currentDate,
            generalexpiredelay: currentDate
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

      it('should delete a Categorydetails', () => {
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
