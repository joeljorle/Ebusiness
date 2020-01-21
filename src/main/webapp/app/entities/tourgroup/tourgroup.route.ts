import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITourgroup, Tourgroup } from 'app/shared/model/tourgroup.model';
import { TourgroupService } from './tourgroup.service';
import { TourgroupComponent } from './tourgroup.component';
import { TourgroupDetailComponent } from './tourgroup-detail.component';
import { TourgroupUpdateComponent } from './tourgroup-update.component';

@Injectable({ providedIn: 'root' })
export class TourgroupResolve implements Resolve<ITourgroup> {
  constructor(private service: TourgroupService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITourgroup> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tourgroup: HttpResponse<Tourgroup>) => {
          if (tourgroup.body) {
            return of(tourgroup.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Tourgroup());
  }
}

export const tourgroupRoute: Routes = [
  {
    path: '',
    component: TourgroupComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinesApp.tourgroup.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TourgroupDetailComponent,
    resolve: {
      tourgroup: TourgroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinesApp.tourgroup.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TourgroupUpdateComponent,
    resolve: {
      tourgroup: TourgroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinesApp.tourgroup.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TourgroupUpdateComponent,
    resolve: {
      tourgroup: TourgroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinesApp.tourgroup.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
