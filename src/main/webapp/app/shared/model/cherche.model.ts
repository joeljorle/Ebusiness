import { Moment } from 'moment';

export interface ICherche {
  id?: number;
  slug?: string;
  name?: string;
  abrev?: string;
  userid?: string;
  details?: string;
  createdat?: Moment;
  updatedat?: Moment;
}

export class Cherche implements ICherche {
  constructor(
    public id?: number,
    public slug?: string,
    public name?: string,
    public abrev?: string,
    public userid?: string,
    public details?: string,
    public createdat?: Moment,
    public updatedat?: Moment
  ) {}
}
