import { Moment } from 'moment';
import { IBookingproducts } from 'app/shared/model/bookingproducts.model';

export interface IBooking {
  id?: number;
  slug?: string;
  userid?: number;
  tourid?: number;
  tourgroupid?: number;
  categoryid?: number;
  productid?: number;
  evenementid?: number;
  createdat?: Moment;
  updatedat?: Moment;
  currencyId?: number;
  bookingviewId?: number;
  bookingdetailsId?: number;
  bookingproducts?: IBookingproducts[];
  categoryId?: number;
  evenementId?: number;
}

export class Booking implements IBooking {
  constructor(
    public id?: number,
    public slug?: string,
    public userid?: number,
    public tourid?: number,
    public tourgroupid?: number,
    public categoryid?: number,
    public productid?: number,
    public evenementid?: number,
    public createdat?: Moment,
    public updatedat?: Moment,
    public currencyId?: number,
    public bookingviewId?: number,
    public bookingdetailsId?: number,
    public bookingproducts?: IBookingproducts[],
    public categoryId?: number,
    public evenementId?: number
  ) {}
}
