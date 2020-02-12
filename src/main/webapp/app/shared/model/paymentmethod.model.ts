import { Moment } from 'moment';
import { IPaymentaction } from 'app/shared/model/paymentaction.model';
import { IPaymentcategory } from 'app/shared/model/paymentcategory.model';

export interface IPaymentmethod {
  id?: number;
  slug?: string;
  name?: string;
  logo?: string;
  logodataContentType?: string;
  logodata?: any;
  createdat?: Moment;
  updatedat?: Moment;
  paymentactions?: IPaymentaction[];
  paymentcategories?: IPaymentcategory[];
}

export class Paymentmethod implements IPaymentmethod {
  constructor(
    public id?: number,
    public slug?: string,
    public name?: string,
    public logo?: string,
    public logodataContentType?: string,
    public logodata?: any,
    public createdat?: Moment,
    public updatedat?: Moment,
    public paymentactions?: IPaymentaction[],
    public paymentcategories?: IPaymentcategory[]
  ) {}
}
