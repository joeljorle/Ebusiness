import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPaymentaction, Paymentaction } from 'app/shared/model/paymentaction.model';
import { PaymentactionService } from './paymentaction.service';
import { PaymentactionComponent } from './paymentaction.component';
import { PaymentactionDetailComponent } from './paymentaction-detail.component';
import { PaymentactionUpdateComponent } from './paymentaction-update.component';

@Injectable({ providedIn: 'root' })
export class PaymentactionResolve implements Resolve<IPaymentaction> {
  constructor(private service: PaymentactionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPaymentaction> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((paymentaction: HttpResponse<Paymentaction>) => {
          if (paymentaction.body) {
            return of(paymentaction.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Paymentaction());
  }
}

export const paymentactionRoute: Routes = [
  {
    path: '',
    component: PaymentactionComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Paymentactions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PaymentactionDetailComponent,
    resolve: {
      paymentaction: PaymentactionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Paymentactions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PaymentactionUpdateComponent,
    resolve: {
      paymentaction: PaymentactionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Paymentactions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PaymentactionUpdateComponent,
    resolve: {
      paymentaction: PaymentactionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Paymentactions'
    },
    canActivate: [UserRouteAccessService]
  }
];
