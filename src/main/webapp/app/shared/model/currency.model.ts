import { Moment } from 'moment';

export interface ICurrency {
  id?: number;
  slug?: string;
  name?: string;
  abrev?: string;
  logo?: any;
  logodataContentType?: string;
  logodata?: any;
  createdat?: Moment;
  updatedat?: Moment;
  categoryId?: number;
}

export class Currency implements ICurrency {
  constructor(
    public id?: number,
    public slug?: string,
    public name?: string,
    public abrev?: string,
    public logo?: any,
    public logodataContentType?: string,
    public logodata?: any,
    public createdat?: Moment,
    public updatedat?: Moment,
    public categoryId?: number
  ) {}
}
