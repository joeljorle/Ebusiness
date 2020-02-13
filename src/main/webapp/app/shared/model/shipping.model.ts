import { Moment } from 'moment';

export interface IShipping {
  id?: number;
  name?: string;
  price?: number;
  createdat?: Moment;
  updatedat?: Moment;
  categoryId?: number;
}

export class Shipping implements IShipping {
  constructor(
    public id?: number,
    public name?: string,
    public price?: number,
    public createdat?: Moment,
    public updatedat?: Moment,
    public categoryId?: number
  ) {}
}
