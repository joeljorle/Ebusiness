import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPaymentcategory, Paymentcategory } from 'app/shared/model/paymentcategory.model';
import { PaymentcategoryService } from './paymentcategory.service';
import { PaymentcategoryComponent } from './paymentcategory.component';
import { PaymentcategoryDetailComponent } from './paymentcategory-detail.component';
import { PaymentcategoryUpdateComponent } from './paymentcategory-update.component';

@Injectable({ providedIn: 'root' })
export class PaymentcategoryResolve implements Resolve<IPaymentcategory> {
  constructor(private service: PaymentcategoryService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPaymentcategory> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((paymentcategory: HttpResponse<Paymentcategory>) => {
          if (paymentcategory.body) {
            return of(paymentcategory.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Paymentcategory());
  }
}

export const paymentcategoryRoute: Routes = [
  {
    path: '',
    component: PaymentcategoryComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinessApp.paymentcategory.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PaymentcategoryDetailComponent,
    resolve: {
      paymentcategory: PaymentcategoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinessApp.paymentcategory.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PaymentcategoryUpdateComponent,
    resolve: {
      paymentcategory: PaymentcategoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinessApp.paymentcategory.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PaymentcategoryUpdateComponent,
    resolve: {
      paymentcategory: PaymentcategoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinessApp.paymentcategory.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
