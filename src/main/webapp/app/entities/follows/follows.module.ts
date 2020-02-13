import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EbusinessSharedModule } from 'app/shared/shared.module';
import { FollowsComponent } from './follows.component';
import { FollowsDetailComponent } from './follows-detail.component';
import { FollowsUpdateComponent } from './follows-update.component';
import { FollowsDeleteDialogComponent } from './follows-delete-dialog.component';
import { followsRoute } from './follows.route';

@NgModule({
  imports: [EbusinessSharedModule, RouterModule.forChild(followsRoute)],
  declarations: [FollowsComponent, FollowsDetailComponent, FollowsUpdateComponent, FollowsDeleteDialogComponent],
  entryComponents: [FollowsDeleteDialogComponent]
})
export class EbusinessFollowsModule {}
