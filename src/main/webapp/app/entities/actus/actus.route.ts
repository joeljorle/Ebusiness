import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IActus, Actus } from 'app/shared/model/actus.model';
import { ActusService } from './actus.service';
import { ActusComponent } from './actus.component';
import { ActusDetailComponent } from './actus-detail.component';
import { ActusUpdateComponent } from './actus-update.component';

@Injectable({ providedIn: 'root' })
export class ActusResolve implements Resolve<IActus> {
  constructor(private service: ActusService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IActus> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((actus: HttpResponse<Actus>) => {
          if (actus.body) {
            return of(actus.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Actus());
  }
}

export const actusRoute: Routes = [
  {
    path: '',
    component: ActusComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinesApp.actus.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ActusDetailComponent,
    resolve: {
      actus: ActusResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinesApp.actus.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ActusUpdateComponent,
    resolve: {
      actus: ActusResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinesApp.actus.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ActusUpdateComponent,
    resolve: {
      actus: ActusResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinesApp.actus.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
