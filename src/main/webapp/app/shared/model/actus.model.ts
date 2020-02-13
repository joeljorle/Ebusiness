import { Moment } from 'moment';

export interface IActus {
  id?: number;
  slug?: string;
  name?: string;
  abrev?: string;
  userid?: string;
  imageContentType?: string;
  image?: any;
  details?: string;
  createdat?: Moment;
  updatedat?: Moment;
}

export class Actus implements IActus {
  constructor(
    public id?: number,
    public slug?: string,
    public name?: string,
    public abrev?: string,
    public userid?: string,
    public imageContentType?: string,
    public image?: any,
    public details?: string,
    public createdat?: Moment,
    public updatedat?: Moment
  ) {}
}
