import { Moment } from 'moment';
import { Tagcolor } from 'app/shared/model/enumerations/tagcolor.model';

export interface IProductdetails {
  id?: number;
  about?: any;
  title?: string;
  tag?: string;
  tagcolor?: Tagcolor;
  defaultlanguage?: string;
  postalcode?: string;
  phones?: string;
  website?: string;
  email?: string;
  facebook?: string;
  twitter?: string;
  gplus?: string;
  linkedin?: string;
  instagram?: string;
  opentimes?: any;
  othercontacts?: any;
  otherfields?: any;
  sizes?: any;
  colors?: any;
  models?: any;
  shippingprice?: number;
  serialnumber?: string;
  tax?: number;
  stock?: number;
  price?: number;
  maxqty?: number;
  availableat?: Moment;
  lockactiondelay?: Moment;
  lockaction?: boolean;
  expireat?: Moment;
}

export class Productdetails implements IProductdetails {
  constructor(
    public id?: number,
    public about?: any,
    public title?: string,
    public tag?: string,
    public tagcolor?: Tagcolor,
    public defaultlanguage?: string,
    public postalcode?: string,
    public phones?: string,
    public website?: string,
    public email?: string,
    public facebook?: string,
    public twitter?: string,
    public gplus?: string,
    public linkedin?: string,
    public instagram?: string,
    public opentimes?: any,
    public othercontacts?: any,
    public otherfields?: any,
    public sizes?: any,
    public colors?: any,
    public models?: any,
    public shippingprice?: number,
    public serialnumber?: string,
    public tax?: number,
    public stock?: number,
    public price?: number,
    public maxqty?: number,
    public availableat?: Moment,
    public lockactiondelay?: Moment,
    public lockaction?: boolean,
    public expireat?: Moment
  ) {
    this.lockaction = this.lockaction || false;
  }
}
