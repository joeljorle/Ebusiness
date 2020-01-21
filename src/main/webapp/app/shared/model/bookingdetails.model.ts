import { Moment } from 'moment';
import { Shippingstate } from 'app/shared/model/enumerations/shippingstate.model';
import { Evenementstate } from 'app/shared/model/enumerations/evenementstate.model';

export interface IBookingdetails {
  id?: number;
  subtotal?: number;
  total?: number;
  tax?: number;
  shipping?: number;
  shippingstate?: Shippingstate;
  about?: string;
  couponconde?: string;
  qrcode?: any;
  code?: string;
  url?: string;
  token?: string;
  paymentmode?: string;
  startdate?: Moment;
  enddate?: Moment;
  enventstate?: Evenementstate;
  places?: number;
}

export class Bookingdetails implements IBookingdetails {
  constructor(
    public id?: number,
    public subtotal?: number,
    public total?: number,
    public tax?: number,
    public shipping?: number,
    public shippingstate?: Shippingstate,
    public about?: string,
    public couponconde?: string,
    public qrcode?: any,
    public code?: string,
    public url?: string,
    public token?: string,
    public paymentmode?: string,
    public startdate?: Moment,
    public enddate?: Moment,
    public enventstate?: Evenementstate,
    public places?: number
  ) {}
}
