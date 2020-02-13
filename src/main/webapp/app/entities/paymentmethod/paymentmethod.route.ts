import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPaymentmethod, Paymentmethod } from 'app/shared/model/paymentmethod.model';
import { PaymentmethodService } from './paymentmethod.service';
import { PaymentmethodComponent } from './paymentmethod.component';
import { PaymentmethodDetailComponent } from './paymentmethod-detail.component';
import { PaymentmethodUpdateComponent } from './paymentmethod-update.component';

@Injectable({ providedIn: 'root' })
export class PaymentmethodResolve implements Resolve<IPaymentmethod> {
  constructor(private service: PaymentmethodService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPaymentmethod> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((paymentmethod: HttpResponse<Paymentmethod>) => {
          if (paymentmethod.body) {
            return of(paymentmethod.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Paymentmethod());
  }
}

export const paymentmethodRoute: Routes = [
  {
    path: '',
    component: PaymentmethodComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinessApp.paymentmethod.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PaymentmethodDetailComponent,
    resolve: {
      paymentmethod: PaymentmethodResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinessApp.paymentmethod.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PaymentmethodUpdateComponent,
    resolve: {
      paymentmethod: PaymentmethodResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinessApp.paymentmethod.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PaymentmethodUpdateComponent,
    resolve: {
      paymentmethod: PaymentmethodResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinessApp.paymentmethod.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
