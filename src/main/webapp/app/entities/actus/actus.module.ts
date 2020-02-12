import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EBusinessSharedModule } from 'app/shared/shared.module';
import { ActusComponent } from './actus.component';
import { ActusDetailComponent } from './actus-detail.component';
import { ActusUpdateComponent } from './actus-update.component';
import { ActusDeleteDialogComponent } from './actus-delete-dialog.component';
import { actusRoute } from './actus.route';

@NgModule({
  imports: [EBusinessSharedModule, RouterModule.forChild(actusRoute)],
  declarations: [ActusComponent, ActusDetailComponent, ActusUpdateComponent, ActusDeleteDialogComponent],
  entryComponents: [ActusDeleteDialogComponent]
})
export class EBusinessActusModule {}
