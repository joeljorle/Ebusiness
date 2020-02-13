import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EbusinessSharedModule } from 'app/shared/shared.module';
import { ProfileComponent } from './profile.component';
import { ProfileDetailComponent } from './profile-detail.component';
import { ProfileUpdateComponent } from './profile-update.component';
import { ProfileDeleteDialogComponent } from './profile-delete-dialog.component';
import { profileRoute } from './profile.route';

@NgModule({
  imports: [EbusinessSharedModule, RouterModule.forChild(profileRoute)],
  declarations: [ProfileComponent, ProfileDetailComponent, ProfileUpdateComponent, ProfileDeleteDialogComponent],
  entryComponents: [ProfileDeleteDialogComponent]
})
export class EbusinessProfileModule {}
