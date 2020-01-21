import { Moment } from 'moment';
import { ITour } from 'app/shared/model/tour.model';
import { IEvenement } from 'app/shared/model/evenement.model';
import { ITourgroup } from 'app/shared/model/tourgroup.model';
import { ITourcategory } from 'app/shared/model/tourcategory.model';
import { ILikes } from 'app/shared/model/likes.model';
import { IFollows } from 'app/shared/model/follows.model';
import { IRatings } from 'app/shared/model/ratings.model';
import { IReviews } from 'app/shared/model/reviews.model';
import { IPaymentaction } from 'app/shared/model/paymentaction.model';
import { Type } from 'app/shared/model/enumerations/type.model';

export interface IProduct {
  id?: number;
  categoryid?: number;
  slug?: string;
  name?: string;
  islock?: boolean;
  lockdelay?: Moment;
  type?: Type;
  createdat?: Moment;
  updatedat?: Moment;
  filesId?: number;
  productdetailsId?: number;
  currencyId?: number;
  locationId?: number;
  tours?: ITour[];
  evenements?: IEvenement[];
  tourgroups?: ITourgroup[];
  tourcategories?: ITourcategory[];
  likes?: ILikes[];
  follows?: IFollows[];
  ratings?: IRatings[];
  reviews?: IReviews[];
  paymentactions?: IPaymentaction[];
  categoryId?: number;
}

export class Product implements IProduct {
  constructor(
    public id?: number,
    public categoryid?: number,
    public slug?: string,
    public name?: string,
    public islock?: boolean,
    public lockdelay?: Moment,
    public type?: Type,
    public createdat?: Moment,
    public updatedat?: Moment,
    public filesId?: number,
    public productdetailsId?: number,
    public currencyId?: number,
    public locationId?: number,
    public tours?: ITour[],
    public evenements?: IEvenement[],
    public tourgroups?: ITourgroup[],
    public tourcategories?: ITourcategory[],
    public likes?: ILikes[],
    public follows?: IFollows[],
    public ratings?: IRatings[],
    public reviews?: IReviews[],
    public paymentactions?: IPaymentaction[],
    public categoryId?: number
  ) {
    this.islock = this.islock || false;
  }
}
