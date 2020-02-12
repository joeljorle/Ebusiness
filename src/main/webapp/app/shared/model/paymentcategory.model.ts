import { Moment } from 'moment';

export interface IPaymentcategory {
  id?: number;
  paymentmethodid?: number;
  categoryid?: number;
  url?: string;
  apiurl?: string;
  apikey?: string;
  key2?: string;
  key3?: string;
  key4?: string;
  phonenumber?: string;
  chanel?: string;
  code?: string;
  username?: string;
  password?: string;
  createdat?: Moment;
  updatedat?: Moment;
  categoryId?: number;
  paymentmethodId?: number;
}

export class Paymentcategory implements IPaymentcategory {
  constructor(
    public id?: number,
    public paymentmethodid?: number,
    public categoryid?: number,
    public url?: string,
    public apiurl?: string,
    public apikey?: string,
    public key2?: string,
    public key3?: string,
    public key4?: string,
    public phonenumber?: string,
    public chanel?: string,
    public code?: string,
    public username?: string,
    public password?: string,
    public createdat?: Moment,
    public updatedat?: Moment,
    public categoryId?: number,
    public paymentmethodId?: number
  ) {}
}
