import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICartproducts, Cartproducts } from 'app/shared/model/cartproducts.model';
import { CartproductsService } from './cartproducts.service';
import { CartproductsComponent } from './cartproducts.component';
import { CartproductsDetailComponent } from './cartproducts-detail.component';
import { CartproductsUpdateComponent } from './cartproducts-update.component';

@Injectable({ providedIn: 'root' })
export class CartproductsResolve implements Resolve<ICartproducts> {
  constructor(private service: CartproductsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICartproducts> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((cartproducts: HttpResponse<Cartproducts>) => {
          if (cartproducts.body) {
            return of(cartproducts.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Cartproducts());
  }
}

export const cartproductsRoute: Routes = [
  {
    path: '',
    component: CartproductsComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinesApp.cartproducts.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CartproductsDetailComponent,
    resolve: {
      cartproducts: CartproductsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinesApp.cartproducts.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CartproductsUpdateComponent,
    resolve: {
      cartproducts: CartproductsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinesApp.cartproducts.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CartproductsUpdateComponent,
    resolve: {
      cartproducts: CartproductsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinesApp.cartproducts.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
