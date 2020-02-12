import { Moment } from 'moment';

export interface IProfile {
  id?: number;
  slug?: string;
  userid?: number;
  name?: string;
  islock?: boolean;
  lockdelay?: Moment;
  about?: any;
  fullname?: string;
  defaultlanguage?: string;
  postalcode?: string;
  phones?: string;
  website?: string;
  facebook?: string;
  twitter?: string;
  gplus?: string;
  linkedin?: string;
  instagram?: string;
  othercontacts?: any;
  otherfields?: any;
  createdat?: Moment;
  updatedat?: Moment;
  userId?: number;
  locationId?: number;
}

export class Profile implements IProfile {
  constructor(
    public id?: number,
    public slug?: string,
    public userid?: number,
    public name?: string,
    public islock?: boolean,
    public lockdelay?: Moment,
    public about?: any,
    public fullname?: string,
    public defaultlanguage?: string,
    public postalcode?: string,
    public phones?: string,
    public website?: string,
    public facebook?: string,
    public twitter?: string,
    public gplus?: string,
    public linkedin?: string,
    public instagram?: string,
    public othercontacts?: any,
    public otherfields?: any,
    public createdat?: Moment,
    public updatedat?: Moment,
    public userId?: number,
    public locationId?: number
  ) {
    this.islock = this.islock || false;
  }
}
