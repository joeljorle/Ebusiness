import { Moment } from 'moment';

export interface IPaymentaction {
  id?: number;
  slug?: string;
  userid?: number;
  paymentmethodid?: number;
  evenementid?: number;
  productid?: number;
  categoryid?: number;
  expireat?: Moment;
  code?: string;
  code1?: string;
  code2?: string;
  amount?: number;
  createdat?: Moment;
  updatedat?: Moment;
  productId?: number;
  evenementId?: number;
  paymentmethodId?: number;
}

export class Paymentaction implements IPaymentaction {
  constructor(
    public id?: number,
    public slug?: string,
    public userid?: number,
    public paymentmethodid?: number,
    public evenementid?: number,
    public productid?: number,
    public categoryid?: number,
    public expireat?: Moment,
    public code?: string,
    public code1?: string,
    public code2?: string,
    public amount?: number,
    public createdat?: Moment,
    public updatedat?: Moment,
    public productId?: number,
    public evenementId?: number,
    public paymentmethodId?: number
  ) {}
}
