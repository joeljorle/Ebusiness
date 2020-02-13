import { Moment } from 'moment';

export interface IReviews {
  id?: number;
  slug?: string;
  userid?: number;
  categoryid?: number;
  productid?: number;
  tourid?: number;
  tourgroupid?: number;
  evenementid?: number;
  text?: string;
  createdat?: Moment;
  updatedat?: Moment;
  categoryId?: number;
  productId?: number;
  evenementId?: number;
}

export class Reviews implements IReviews {
  constructor(
    public id?: number,
    public slug?: string,
    public userid?: number,
    public categoryid?: number,
    public productid?: number,
    public tourid?: number,
    public tourgroupid?: number,
    public evenementid?: number,
    public text?: string,
    public createdat?: Moment,
    public updatedat?: Moment,
    public categoryId?: number,
    public productId?: number,
    public evenementId?: number
  ) {}
}
