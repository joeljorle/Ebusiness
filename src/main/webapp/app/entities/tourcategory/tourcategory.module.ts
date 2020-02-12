import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EBusinessSharedModule } from 'app/shared/shared.module';
import { TourcategoryComponent } from './tourcategory.component';
import { TourcategoryDetailComponent } from './tourcategory-detail.component';
import { TourcategoryUpdateComponent } from './tourcategory-update.component';
import { TourcategoryDeleteDialogComponent } from './tourcategory-delete-dialog.component';
import { tourcategoryRoute } from './tourcategory.route';

@NgModule({
  imports: [EBusinessSharedModule, RouterModule.forChild(tourcategoryRoute)],
  declarations: [TourcategoryComponent, TourcategoryDetailComponent, TourcategoryUpdateComponent, TourcategoryDeleteDialogComponent],
  entryComponents: [TourcategoryDeleteDialogComponent]
})
export class EBusinessTourcategoryModule {}
