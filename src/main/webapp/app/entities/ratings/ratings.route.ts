import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRatings, Ratings } from 'app/shared/model/ratings.model';
import { RatingsService } from './ratings.service';
import { RatingsComponent } from './ratings.component';
import { RatingsDetailComponent } from './ratings-detail.component';
import { RatingsUpdateComponent } from './ratings-update.component';

@Injectable({ providedIn: 'root' })
export class RatingsResolve implements Resolve<IRatings> {
  constructor(private service: RatingsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRatings> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ratings: HttpResponse<Ratings>) => {
          if (ratings.body) {
            return of(ratings.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Ratings());
  }
}

export const ratingsRoute: Routes = [
  {
    path: '',
    component: RatingsComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Ratings'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: RatingsDetailComponent,
    resolve: {
      ratings: RatingsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Ratings'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: RatingsUpdateComponent,
    resolve: {
      ratings: RatingsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Ratings'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: RatingsUpdateComponent,
    resolve: {
      ratings: RatingsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Ratings'
    },
    canActivate: [UserRouteAccessService]
  }
];
