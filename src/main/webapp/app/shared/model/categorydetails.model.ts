import { Moment } from 'moment';
import { Tagcolor } from 'app/shared/model/enumerations/tagcolor.model';

export interface ICategorydetails {
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
  generalabout?: boolean;
  generaltax?: number;
  generalstock?: number;
  generalprice?: number;
  generalmaxstock?: number;
  generaltag?: string;
  generaltagcolor?: Tagcolor;
  generalhidden?: boolean;
  generalhiddendelay?: Moment;
  generallock?: boolean;
  generallockdelay?: Moment;
  generalexpire?: boolean;
  generalexpiredelay?: Moment;
  childsname?: string;
  productsname?: string;
}

export class Categorydetails implements ICategorydetails {
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
    public generalabout?: boolean,
    public generaltax?: number,
    public generalstock?: number,
    public generalprice?: number,
    public generalmaxstock?: number,
    public generaltag?: string,
    public generaltagcolor?: Tagcolor,
    public generalhidden?: boolean,
    public generalhiddendelay?: Moment,
    public generallock?: boolean,
    public generallockdelay?: Moment,
    public generalexpire?: boolean,
    public generalexpiredelay?: Moment,
    public childsname?: string,
    public productsname?: string
  ) {
    this.generalabout = this.generalabout || false;
    this.generalhidden = this.generalhidden || false;
    this.generallock = this.generallock || false;
    this.generalexpire = this.generalexpire || false;
  }
}
