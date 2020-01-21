import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EbusinesSharedModule } from 'app/shared/shared.module';
import { CategorydetailsComponent } from './categorydetails.component';
import { CategorydetailsDetailComponent } from './categorydetails-detail.component';
import { CategorydetailsUpdateComponent } from './categorydetails-update.component';
import { CategorydetailsDeleteDialogComponent } from './categorydetails-delete-dialog.component';
import { categorydetailsRoute } from './categorydetails.route';

@NgModule({
  imports: [EbusinesSharedModule, RouterModule.forChild(categorydetailsRoute)],
  declarations: [
    CategorydetailsComponent,
    CategorydetailsDetailComponent,
    CategorydetailsUpdateComponent,
    CategorydetailsDeleteDialogComponent
  ],
  entryComponents: [CategorydetailsDeleteDialogComponent]
})
export class EbusinesCategorydetailsModule {}
