import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBookingdetails, Bookingdetails } from 'app/shared/model/bookingdetails.model';
import { BookingdetailsService } from './bookingdetails.service';
import { BookingdetailsComponent } from './bookingdetails.component';
import { BookingdetailsDetailComponent } from './bookingdetails-detail.component';
import { BookingdetailsUpdateComponent } from './bookingdetails-update.component';

@Injectable({ providedIn: 'root' })
export class BookingdetailsResolve implements Resolve<IBookingdetails> {
  constructor(private service: BookingdetailsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBookingdetails> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((bookingdetails: HttpResponse<Bookingdetails>) => {
          if (bookingdetails.body) {
            return of(bookingdetails.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Bookingdetails());
  }
}

export const bookingdetailsRoute: Routes = [
  {
    path: '',
    component: BookingdetailsComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinessApp.bookingdetails.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: BookingdetailsDetailComponent,
    resolve: {
      bookingdetails: BookingdetailsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinessApp.bookingdetails.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: BookingdetailsUpdateComponent,
    resolve: {
      bookingdetails: BookingdetailsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinessApp.bookingdetails.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: BookingdetailsUpdateComponent,
    resolve: {
      bookingdetails: BookingdetailsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinessApp.bookingdetails.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
