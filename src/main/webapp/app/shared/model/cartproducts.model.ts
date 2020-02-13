export interface ICartproducts {
  id?: number;
  cartid?: number;
  productid?: number;
  cartqty?: number;
  cartId?: number;
}

export class Cartproducts implements ICartproducts {
  constructor(public id?: number, public cartid?: number, public productid?: number, public cartqty?: number, public cartId?: number) {}
}
