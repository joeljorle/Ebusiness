import { Moment } from 'moment';

export interface IFollows {
  id?: number;
  slug?: string;
  userid?: number;
  categoryid?: number;
  productid?: number;
  tourid?: number;
  evenementid?: number;
  tourgroupid?: number;
  alert?: boolean;
  alertEvenement?: boolean;
  followchild?: boolean;
  createdat?: Moment;
  updatedat?: Moment;
  categoryId?: number;
  productId?: number;
  evenementId?: number;
}

export class Follows implements IFollows {
  constructor(
    public id?: number,
    public slug?: string,
    public userid?: number,
    public categoryid?: number,
    public productid?: number,
    public tourid?: number,
    public evenementid?: number,
    public tourgroupid?: number,
    public alert?: boolean,
    public alertEvenement?: boolean,
    public followchild?: boolean,
    public createdat?: Moment,
    public updatedat?: Moment,
    public categoryId?: number,
    public productId?: number,
    public evenementId?: number
  ) {
    this.alert = this.alert || false;
    this.alertEvenement = this.alertEvenement || false;
    this.followchild = this.followchild || false;
  }
}
