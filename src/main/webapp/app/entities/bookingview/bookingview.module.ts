import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EbusinessSharedModule } from 'app/shared/shared.module';
import { BookingviewComponent } from './bookingview.component';
import { BookingviewDetailComponent } from './bookingview-detail.component';
import { BookingviewUpdateComponent } from './bookingview-update.component';
import { BookingviewDeleteDialogComponent } from './bookingview-delete-dialog.component';
import { bookingviewRoute } from './bookingview.route';

@NgModule({
  imports: [EbusinessSharedModule, RouterModule.forChild(bookingviewRoute)],
  declarations: [BookingviewComponent, BookingviewDetailComponent, BookingviewUpdateComponent, BookingviewDeleteDialogComponent],
  entryComponents: [BookingviewDeleteDialogComponent]
})
export class EbusinessBookingviewModule {}
