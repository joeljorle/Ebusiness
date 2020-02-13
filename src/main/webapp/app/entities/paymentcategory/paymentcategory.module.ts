import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EbusinessSharedModule } from 'app/shared/shared.module';
import { PaymentcategoryComponent } from './paymentcategory.component';
import { PaymentcategoryDetailComponent } from './paymentcategory-detail.component';
import { PaymentcategoryUpdateComponent } from './paymentcategory-update.component';
import { PaymentcategoryDeleteDialogComponent } from './paymentcategory-delete-dialog.component';
import { paymentcategoryRoute } from './paymentcategory.route';

@NgModule({
  imports: [EbusinessSharedModule, RouterModule.forChild(paymentcategoryRoute)],
  declarations: [
    PaymentcategoryComponent,
    PaymentcategoryDetailComponent,
    PaymentcategoryUpdateComponent,
    PaymentcategoryDeleteDialogComponent
  ],
  entryComponents: [PaymentcategoryDeleteDialogComponent]
})
export class EbusinessPaymentcategoryModule {}
