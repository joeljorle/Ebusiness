import { Moment } from 'moment';

export interface IRatings {
  id?: number;
  slug?: string;
  userid?: number;
  categoryid?: number;
  productid?: number;
  tourid?: number;
  tourgroupid?: number;
  evenementid?: number;
  value?: number;
  createdat?: Moment;
  updatedat?: Moment;
  categoryId?: number;
  productId?: number;
  evenementId?: number;
}

export class Ratings implements IRatings {
  constructor(
    public id?: number,
    public slug?: string,
    public userid?: number,
    public categoryid?: number,
    public productid?: number,
    public tourid?: number,
    public tourgroupid?: number,
    public evenementid?: number,
    public value?: number,
    public createdat?: Moment,
    public updatedat?: Moment,
    public categoryId?: number,
    public productId?: number,
    public evenementId?: number
  ) {}
}
