import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EBusinessSharedModule } from 'app/shared/shared.module';
import { PaymentmethodComponent } from './paymentmethod.component';
import { PaymentmethodDetailComponent } from './paymentmethod-detail.component';
import { PaymentmethodUpdateComponent } from './paymentmethod-update.component';
import { PaymentmethodDeleteDialogComponent } from './paymentmethod-delete-dialog.component';
import { paymentmethodRoute } from './paymentmethod.route';

@NgModule({
  imports: [EBusinessSharedModule, RouterModule.forChild(paymentmethodRoute)],
  declarations: [PaymentmethodComponent, PaymentmethodDetailComponent, PaymentmethodUpdateComponent, PaymentmethodDeleteDialogComponent],
  entryComponents: [PaymentmethodDeleteDialogComponent]
})
export class EBusinessPaymentmethodModule {}
