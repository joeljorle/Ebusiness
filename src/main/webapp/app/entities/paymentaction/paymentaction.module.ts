import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EbusinesSharedModule } from 'app/shared/shared.module';
import { PaymentactionComponent } from './paymentaction.component';
import { PaymentactionDetailComponent } from './paymentaction-detail.component';
import { PaymentactionUpdateComponent } from './paymentaction-update.component';
import { PaymentactionDeleteDialogComponent } from './paymentaction-delete-dialog.component';
import { paymentactionRoute } from './paymentaction.route';

@NgModule({
  imports: [EbusinesSharedModule, RouterModule.forChild(paymentactionRoute)],
  declarations: [PaymentactionComponent, PaymentactionDetailComponent, PaymentactionUpdateComponent, PaymentactionDeleteDialogComponent],
  entryComponents: [PaymentactionDeleteDialogComponent]
})
export class EbusinesPaymentactionModule {}
