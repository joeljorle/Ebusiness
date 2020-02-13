import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EbusinessSharedModule } from 'app/shared/shared.module';
import { BookingdetailsComponent } from './bookingdetails.component';
import { BookingdetailsDetailComponent } from './bookingdetails-detail.component';
import { BookingdetailsUpdateComponent } from './bookingdetails-update.component';
import { BookingdetailsDeleteDialogComponent } from './bookingdetails-delete-dialog.component';
import { bookingdetailsRoute } from './bookingdetails.route';

@NgModule({
  imports: [EbusinessSharedModule, RouterModule.forChild(bookingdetailsRoute)],
  declarations: [
    BookingdetailsComponent,
    BookingdetailsDetailComponent,
    BookingdetailsUpdateComponent,
    BookingdetailsDeleteDialogComponent
  ],
  entryComponents: [BookingdetailsDeleteDialogComponent]
})
export class EbusinessBookingdetailsModule {}
