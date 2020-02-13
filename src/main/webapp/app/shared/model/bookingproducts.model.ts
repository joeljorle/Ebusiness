export interface IBookingproducts {
  id?: number;
  bookingid?: number;
  productid?: number;
  bookingqty?: number;
  bookingId?: number;
}

export class Bookingproducts implements IBookingproducts {
  constructor(
    public id?: number,
    public bookingid?: number,
    public productid?: number,
    public bookingqty?: number,
    public bookingId?: number
  ) {}
}
