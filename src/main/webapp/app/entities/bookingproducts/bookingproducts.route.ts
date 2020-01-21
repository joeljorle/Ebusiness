import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBookingproducts, Bookingproducts } from 'app/shared/model/bookingproducts.model';
import { BookingproductsService } from './bookingproducts.service';
import { BookingproductsComponent } from './bookingproducts.component';
import { BookingproductsDetailComponent } from './bookingproducts-detail.component';
import { BookingproductsUpdateComponent } from './bookingproducts-update.component';

@Injectable({ providedIn: 'root' })
export class BookingproductsResolve implements Resolve<IBookingproducts> {
  constructor(private service: BookingproductsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBookingproducts> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((bookingproducts: HttpResponse<Bookingproducts>) => {
          if (bookingproducts.body) {
            return of(bookingproducts.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Bookingproducts());
  }
}

export const bookingproductsRoute: Routes = [
  {
    path: '',
    component: BookingproductsComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinesApp.bookingproducts.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: BookingproductsDetailComponent,
    resolve: {
      bookingproducts: BookingproductsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinesApp.bookingproducts.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: BookingproductsUpdateComponent,
    resolve: {
      bookingproducts: BookingproductsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinesApp.bookingproducts.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: BookingproductsUpdateComponent,
    resolve: {
      bookingproducts: BookingproductsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinesApp.bookingproducts.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
