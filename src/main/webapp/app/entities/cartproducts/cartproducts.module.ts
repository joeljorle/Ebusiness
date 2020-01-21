import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EbusinesSharedModule } from 'app/shared/shared.module';
import { CartproductsComponent } from './cartproducts.component';
import { CartproductsDetailComponent } from './cartproducts-detail.component';
import { CartproductsUpdateComponent } from './cartproducts-update.component';
import { CartproductsDeleteDialogComponent } from './cartproducts-delete-dialog.component';
import { cartproductsRoute } from './cartproducts.route';

@NgModule({
  imports: [EbusinesSharedModule, RouterModule.forChild(cartproductsRoute)],
  declarations: [CartproductsComponent, CartproductsDetailComponent, CartproductsUpdateComponent, CartproductsDeleteDialogComponent],
  entryComponents: [CartproductsDeleteDialogComponent]
})
export class EbusinesCartproductsModule {}
