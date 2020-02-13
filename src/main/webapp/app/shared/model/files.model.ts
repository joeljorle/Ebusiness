import { Moment } from 'moment';

export interface IFiles {
  id?: number;
  slug?: string;
  userid?: number;
  categoryid?: number;
  productid?: number;
  tourid?: number;
  tourgroupid?: number;
  tourcategoryid?: number;
  evenementid?: number;
  islogoimg?: boolean;
  isprofileimg?: boolean;
  iscoverimg?: boolean;
  issliderimg?: boolean;
  isotherimg?: boolean;
  createdat?: Moment;
  updatedat?: Moment;
}

export class Files implements IFiles {
  constructor(
    public id?: number,
    public slug?: string,
    public userid?: number,
    public categoryid?: number,
    public productid?: number,
    public tourid?: number,
    public tourgroupid?: number,
    public tourcategoryid?: number,
    public evenementid?: number,
    public islogoimg?: boolean,
    public isprofileimg?: boolean,
    public iscoverimg?: boolean,
    public issliderimg?: boolean,
    public isotherimg?: boolean,
    public createdat?: Moment,
    public updatedat?: Moment
  ) {
    this.islogoimg = this.islogoimg || false;
    this.isprofileimg = this.isprofileimg || false;
    this.iscoverimg = this.iscoverimg || false;
    this.issliderimg = this.issliderimg || false;
    this.isotherimg = this.isotherimg || false;
  }
}
