import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ProductdetailsService } from 'app/entities/productdetails/productdetails.service';
import { IProductdetails, Productdetails } from 'app/shared/model/productdetails.model';
import { Tagcolor } from 'app/shared/model/enumerations/tagcolor.model';

describe('Service Tests', () => {
  describe('Productdetails Service', () => {
    let injector: TestBed;
    let service: ProductdetailsService;
    let httpMock: HttpTestingController;
    let elemDefault: IProductdetails;
    let expectedResult: IProductdetails | IProductdetails[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ProductdetailsService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Productdetails(
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
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        0,
        0,
        0,
        0,
        currentDate,
        currentDate,
        false,
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            availableat: currentDate.format(DATE_TIME_FORMAT),
            lockactiondelay: currentDate.format(DATE_TIME_FORMAT),
            expireat: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Productdetails', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            availableat: currentDate.format(DATE_TIME_FORMAT),
            lockactiondelay: currentDate.format(DATE_TIME_FORMAT),
            expireat: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            availableat: currentDate,
            lockactiondelay: currentDate,
            expireat: currentDate
          },
          returnedFromService
        );

        service.create(new Productdetails()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Productdetails', () => {
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
            sizes: 'BBBBBB',
            colors: 'BBBBBB',
            models: 'BBBBBB',
            shippingprice: 1,
            serialnumber: 'BBBBBB',
            tax: 1,
            stock: 1,
            price: 1,
            maxqty: 1,
            availableat: currentDate.format(DATE_TIME_FORMAT),
            lockactiondelay: currentDate.format(DATE_TIME_FORMAT),
            lockaction: true,
            expireat: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            availableat: currentDate,
            lockactiondelay: currentDate,
            expireat: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Productdetails', () => {
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
            sizes: 'BBBBBB',
            colors: 'BBBBBB',
            models: 'BBBBBB',
            shippingprice: 1,
            serialnumber: 'BBBBBB',
            tax: 1,
            stock: 1,
            price: 1,
            maxqty: 1,
            availableat: currentDate.format(DATE_TIME_FORMAT),
            lockactiondelay: currentDate.format(DATE_TIME_FORMAT),
            lockaction: true,
            expireat: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            availableat: currentDate,
            lockactiondelay: currentDate,
            expireat: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Productdetails', () => {
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
