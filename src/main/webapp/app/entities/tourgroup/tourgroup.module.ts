import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EBusinessSharedModule } from 'app/shared/shared.module';
import { TourgroupComponent } from './tourgroup.component';
import { TourgroupDetailComponent } from './tourgroup-detail.component';
import { TourgroupUpdateComponent } from './tourgroup-update.component';
import { TourgroupDeleteDialogComponent } from './tourgroup-delete-dialog.component';
import { tourgroupRoute } from './tourgroup.route';

@NgModule({
  imports: [EBusinessSharedModule, RouterModule.forChild(tourgroupRoute)],
  declarations: [TourgroupComponent, TourgroupDetailComponent, TourgroupUpdateComponent, TourgroupDeleteDialogComponent],
  entryComponents: [TourgroupDeleteDialogComponent]
})
export class EBusinessTourgroupModule {}
