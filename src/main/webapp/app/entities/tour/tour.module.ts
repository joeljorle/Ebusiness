import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EbusinessSharedModule } from 'app/shared/shared.module';
import { TourComponent } from './tour.component';
import { TourDetailComponent } from './tour-detail.component';
import { TourUpdateComponent } from './tour-update.component';
import { TourDeleteDialogComponent } from './tour-delete-dialog.component';
import { tourRoute } from './tour.route';

@NgModule({
  imports: [EbusinessSharedModule, RouterModule.forChild(tourRoute)],
  declarations: [TourComponent, TourDetailComponent, TourUpdateComponent, TourDeleteDialogComponent],
  entryComponents: [TourDeleteDialogComponent]
})
export class EbusinessTourModule {}
