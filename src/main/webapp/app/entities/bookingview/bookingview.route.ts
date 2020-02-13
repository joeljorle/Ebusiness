import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBookingview, Bookingview } from 'app/shared/model/bookingview.model';
import { BookingviewService } from './bookingview.service';
import { BookingviewComponent } from './bookingview.component';
import { BookingviewDetailComponent } from './bookingview-detail.component';
import { BookingviewUpdateComponent } from './bookingview-update.component';

@Injectable({ providedIn: 'root' })
export class BookingviewResolve implements Resolve<IBookingview> {
  constructor(private service: BookingviewService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBookingview> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((bookingview: HttpResponse<Bookingview>) => {
          if (bookingview.body) {
            return of(bookingview.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Bookingview());
  }
}

export const bookingviewRoute: Routes = [
  {
    path: '',
    component: BookingviewComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinessApp.bookingview.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: BookingviewDetailComponent,
    resolve: {
      bookingview: BookingviewResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinessApp.bookingview.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: BookingviewUpdateComponent,
    resolve: {
      bookingview: BookingviewResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinessApp.bookingview.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: BookingviewUpdateComponent,
    resolve: {
      bookingview: BookingviewResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinessApp.bookingview.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
