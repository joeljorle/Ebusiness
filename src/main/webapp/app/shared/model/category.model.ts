import { Moment } from 'moment';
import { IProduct } from 'app/shared/model/product.model';
import { IEvenement } from 'app/shared/model/evenement.model';
import { ITour } from 'app/shared/model/tour.model';
import { ILikes } from 'app/shared/model/likes.model';
import { IFollows } from 'app/shared/model/follows.model';
import { IRatings } from 'app/shared/model/ratings.model';
import { IReviews } from 'app/shared/model/reviews.model';
import { ICategory } from 'app/shared/model/category.model';
import { ITourgroup } from 'app/shared/model/tourgroup.model';
import { ITourcategory } from 'app/shared/model/tourcategory.model';
import { IBooking } from 'app/shared/model/booking.model';
import { ICart } from 'app/shared/model/cart.model';
import { IPaymentcategory } from 'app/shared/model/paymentcategory.model';
import { ICurrency } from 'app/shared/model/currency.model';
import { IShipping } from 'app/shared/model/shipping.model';
import { Type } from 'app/shared/model/enumerations/type.model';

export interface ICategory {
  id?: number;
  slug?: string;
  userid?: number;
  categoryid?: number;
  name?: string;
  route?: string;
  isheader?: boolean;
  isgroup?: boolean;
  islock?: boolean;
  lockdelay?: Moment;
  type?: Type;
  createdat?: Moment;
  updatedat?: Moment;
  filesId?: number;
  categorydetailsId?: number;
  locationId?: number;
  products?: IProduct[];
  evenements?: IEvenement[];
  tours?: ITour[];
  likes?: ILikes[];
  follows?: IFollows[];
  ratings?: IRatings[];
  reviews?: IReviews[];
  categories?: ICategory[];
  tourgroups?: ITourgroup[];
  tourcategories?: ITourcategory[];
  bookings?: IBooking[];
  carts?: ICart[];
  paymentcategories?: IPaymentcategory[];
  currencies?: ICurrency[];
  shippings?: IShipping[];
  categoryId?: number;
}

export class Category implements ICategory {
  constructor(
    public id?: number,
    public slug?: string,
    public userid?: number,
    public categoryid?: number,
    public name?: string,
    public route?: string,
    public isheader?: boolean,
    public isgroup?: boolean,
    public islock?: boolean,
    public lockdelay?: Moment,
    public type?: Type,
    public createdat?: Moment,
    public updatedat?: Moment,
    public filesId?: number,
    public categorydetailsId?: number,
    public locationId?: number,
    public products?: IProduct[],
    public evenements?: IEvenement[],
    public tours?: ITour[],
    public likes?: ILikes[],
    public follows?: IFollows[],
    public ratings?: IRatings[],
    public reviews?: IReviews[],
    public categories?: ICategory[],
    public tourgroups?: ITourgroup[],
    public tourcategories?: ITourcategory[],
    public bookings?: IBooking[],
    public carts?: ICart[],
    public paymentcategories?: IPaymentcategory[],
    public currencies?: ICurrency[],
    public shippings?: IShipping[],
    public categoryId?: number
  ) {
    this.isheader = this.isheader || false;
    this.isgroup = this.isgroup || false;
    this.islock = this.islock || false;
  }
}
