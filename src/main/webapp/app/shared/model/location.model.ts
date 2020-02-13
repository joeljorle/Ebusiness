import { Moment } from 'moment';

export interface ILocation {
  id?: number;
  country?: string;
  town?: string;
  region?: string;
  locality?: string;
  zone?: string;
  lattitude?: string;
  longitude?: string;
  createdat?: Moment;
  updatedat?: Moment;
  categoryId?: number;
  productId?: number;
  evenementId?: number;
  profileId?: number;
  tourId?: number;
  tourgroupId?: number;
}

export class Location implements ILocation {
  constructor(
    public id?: number,
    public country?: string,
    public town?: string,
    public region?: string,
    public locality?: string,
    public zone?: string,
    public lattitude?: string,
    public longitude?: string,
    public createdat?: Moment,
    public updatedat?: Moment,
    public categoryId?: number,
    public productId?: number,
    public evenementId?: number,
    public profileId?: number,
    public tourId?: number,
    public tourgroupId?: number
  ) {}
}
