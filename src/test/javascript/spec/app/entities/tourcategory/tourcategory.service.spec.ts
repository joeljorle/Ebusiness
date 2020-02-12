import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { TourcategoryService } from 'app/entities/tourcategory/tourcategory.service';
import { ITourcategory, Tourcategory } from 'app/shared/model/tourcategory.model';
import { Tagcolor } from 'app/shared/model/enumerations/tagcolor.model';

describe('Service Tests', () => {
  describe('Tourcategory Service', () => {
    let injector: TestBed;
    let service: TourcategoryService;
    let httpMock: HttpTestingController;
    let elemDefault: ITourcategory;
    let expectedResult: ITourcategory | ITourcategory[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TourcategoryService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Tourcategory(
        0,
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        false,
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
        currentDate,
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            lockdelay: currentDate.format(DATE_TIME_FORMAT),
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

      it('should create a Tourcategory', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            lockdelay: currentDate.format(DATE_TIME_FORMAT),
            createdat: currentDate.format(DATE_TIME_FORMAT),
            updatedat: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            lockdelay: currentDate,
            createdat: currentDate,
            updatedat: currentDate
          },
          returnedFromService
        );

        service.create(new Tourcategory()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Tourcategory', () => {
        const returnedFromService = Object.assign(
          {
            categoryid: 1,
            productid: 1,
            evenementid: 1,
            slug: 'BBBBBB',
            name: 'BBBBBB',
            islock: true,
            lockdelay: currentDate.format(DATE_TIME_FORMAT),
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

      it('should return a list of Tourcategory', () => {
        const returnedFromService = Object.assign(
          {
            categoryid: 1,
            productid: 1,
            evenementid: 1,
            slug: 'BBBBBB',
            name: 'BBBBBB',
            islock: true,
            lockdelay: currentDate.format(DATE_TIME_FORMAT),
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

      it('should delete a Tourcategory', () => {
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
