export interface IBookingview {
  id?: number;
  title?: string;
  subtitle?: string;
  logo?: string;
  logodataContentType?: string;
  logodata?: any;
  bg?: string;
  bgdataContentType?: string;
  bgdata?: any;
}

export class Bookingview implements IBookingview {
  constructor(
    public id?: number,
    public title?: string,
    public subtitle?: string,
    public logo?: string,
    public logodataContentType?: string,
    public logodata?: any,
    public bg?: string,
    public bgdataContentType?: string,
    public bgdata?: any
  ) {}
}
