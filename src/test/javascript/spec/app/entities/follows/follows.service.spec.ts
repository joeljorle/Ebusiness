import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { FollowsService } from 'app/entities/follows/follows.service';
import { IFollows, Follows } from 'app/shared/model/follows.model';

describe('Service Tests', () => {
  describe('Follows Service', () => {
    let injector: TestBed;
    let service: FollowsService;
    let httpMock: HttpTestingController;
    let elemDefault: IFollows;
    let expectedResult: IFollows | IFollows[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(FollowsService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Follows(0, 'AAAAAAA', 0, 0, 0, 0, 0, 0, false, false, false, currentDate, currentDate);
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

      it('should create a Follows', () => {
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

        service.create(new Follows()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Follows', () => {
        const returnedFromService = Object.assign(
          {
            slug: 'BBBBBB',
            userid: 1,
            categoryid: 1,
            productid: 1,
            tourid: 1,
            evenementid: 1,
            tourgroupid: 1,
            alert: true,
            alertEvenement: true,
            followchild: true,
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

      it('should return a list of Follows', () => {
        const returnedFromService = Object.assign(
          {
            slug: 'BBBBBB',
            userid: 1,
            categoryid: 1,
            productid: 1,
            tourid: 1,
            evenementid: 1,
            tourgroupid: 1,
            alert: true,
            alertEvenement: true,
            followchild: true,
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

      it('should delete a Follows', () => {
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
