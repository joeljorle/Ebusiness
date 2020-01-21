import { Moment } from 'moment';
import { Notificationmodel } from 'app/shared/model/enumerations/notificationmodel.model';

export interface INotification {
  id?: number;
  slug?: string;
  userid?: number;
  urltarget?: string;
  message?: string;
  title?: string;
  logo?: string;
  logodataContentType?: string;
  logodata?: any;
  isview?: boolean;
  notificationmodel?: Notificationmodel;
  createdat?: Moment;
  updatedat?: Moment;
}

export class Notification implements INotification {
  constructor(
    public id?: number,
    public slug?: string,
    public userid?: number,
    public urltarget?: string,
    public message?: string,
    public title?: string,
    public logo?: string,
    public logodataContentType?: string,
    public logodata?: any,
    public isview?: boolean,
    public notificationmodel?: Notificationmodel,
    public createdat?: Moment,
    public updatedat?: Moment
  ) {
    this.isview = this.isview || false;
  }
}
