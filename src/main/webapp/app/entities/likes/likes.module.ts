import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EBusinessSharedModule } from 'app/shared/shared.module';
import { LikesComponent } from './likes.component';
import { LikesDetailComponent } from './likes-detail.component';
import { LikesUpdateComponent } from './likes-update.component';
import { LikesDeleteDialogComponent } from './likes-delete-dialog.component';
import { likesRoute } from './likes.route';

@NgModule({
  imports: [EBusinessSharedModule, RouterModule.forChild(likesRoute)],
  declarations: [LikesComponent, LikesDetailComponent, LikesUpdateComponent, LikesDeleteDialogComponent],
  entryComponents: [LikesDeleteDialogComponent]
})
export class EBusinessLikesModule {}
