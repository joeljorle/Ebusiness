import { Moment } from 'moment';
import { ICartproducts } from 'app/shared/model/cartproducts.model';

export interface ICart {
  id?: number;
  slug?: string;
  categoryid?: number;
  userid?: number;
  totalprice?: number;
  createdat?: Moment;
  updatedat?: Moment;
  currencyId?: number;
  cartproducts?: ICartproducts[];
  categoryId?: number;
}

export class Cart implements ICart {
  constructor(
    public id?: number,
    public slug?: string,
    public categoryid?: number,
    public userid?: number,
    public totalprice?: number,
    public createdat?: Moment,
    public updatedat?: Moment,
    public currencyId?: number,
    public cartproducts?: ICartproducts[],
    public categoryId?: number
  ) {}
}
