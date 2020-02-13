import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EbusinessSharedModule } from 'app/shared/shared.module';
import { RatingsComponent } from './ratings.component';
import { RatingsDetailComponent } from './ratings-detail.component';
import { RatingsUpdateComponent } from './ratings-update.component';
import { RatingsDeleteDialogComponent } from './ratings-delete-dialog.component';
import { ratingsRoute } from './ratings.route';

@NgModule({
  imports: [EbusinessSharedModule, RouterModule.forChild(ratingsRoute)],
  declarations: [RatingsComponent, RatingsDetailComponent, RatingsUpdateComponent, RatingsDeleteDialogComponent],
  entryComponents: [RatingsDeleteDialogComponent]
})
export class EbusinessRatingsModule {}
