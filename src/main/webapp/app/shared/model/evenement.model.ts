import { Moment } from 'moment';
import { ITour } from 'app/shared/model/tour.model';
import { ITourgroup } from 'app/shared/model/tourgroup.model';
import { ITourcategory } from 'app/shared/model/tourcategory.model';
import { ILikes } from 'app/shared/model/likes.model';
import { IRatings } from 'app/shared/model/ratings.model';
import { IReviews } from 'app/shared/model/reviews.model';
import { IFollows } from 'app/shared/model/follows.model';
import { IBooking } from 'app/shared/model/booking.model';
import { IPaymentaction } from 'app/shared/model/paymentaction.model';
import { Tagcolor } from 'app/shared/model/enumerations/tagcolor.model';

export interface IEvenement {
  id?: number;
  categoryid?: number;
  userid?: number;
  productid?: number;
  slug?: string;
  name?: string;
  islock?: boolean;
  lockdelay?: Moment;
  islimited?: boolean;
  limiteddelay?: Moment;
  limitedbooking?: number;
  startdate?: Moment;
  enddate?: Moment;
  about?: any;
  title?: string;
  tag?: string;
  tagcolor?: Tagcolor;
  postalcode?: string;
  phones?: string;
  website?: string;
  facebook?: string;
  twitter?: string;
  gplus?: string;
  linkedin?: string;
  instagram?: string;
  email?: string;
  url?: string;
  othercontacts?: any;
  otherfields?: any;
  createdat?: Moment;
  updatedat?: Moment;
  filesId?: number;
  currencyId?: number;
  locationId?: number;
  tours?: ITour[];
  tourgroups?: ITourgroup[];
  tourcategories?: ITourcategory[];
  likes?: ILikes[];
  ratings?: IRatings[];
  reviews?: IReviews[];
  follows?: IFollows[];
  bookings?: IBooking[];
  paymentactions?: IPaymentaction[];
  categoryId?: number;
  productId?: number;
}

export class Evenement implements IEvenement {
  constructor(
    public id?: number,
    public categoryid?: number,
    public userid?: number,
    public productid?: number,
    public slug?: string,
    public name?: string,
    public islock?: boolean,
    public lockdelay?: Moment,
    public islimited?: boolean,
    public limiteddelay?: Moment,
    public limitedbooking?: number,
    public startdate?: Moment,
    public enddate?: Moment,
    public about?: any,
    public title?: string,
    public tag?: string,
    public tagcolor?: Tagcolor,
    public postalcode?: string,
    public phones?: string,
    public website?: string,
    public facebook?: string,
    public twitter?: string,
    public gplus?: string,
    public linkedin?: string,
    public instagram?: string,
    public email?: string,
    public url?: string,
    public othercontacts?: any,
    public otherfields?: any,
    public createdat?: Moment,
    public updatedat?: Moment,
    public filesId?: number,
    public currencyId?: number,
    public locationId?: number,
    public tours?: ITour[],
    public tourgroups?: ITourgroup[],
    public tourcategories?: ITourcategory[],
    public likes?: ILikes[],
    public ratings?: IRatings[],
    public reviews?: IReviews[],
    public follows?: IFollows[],
    public bookings?: IBooking[],
    public paymentactions?: IPaymentaction[],
    public categoryId?: number,
    public productId?: number
  ) {
    this.islock = this.islock || false;
    this.islimited = this.islimited || false;
  }
}
