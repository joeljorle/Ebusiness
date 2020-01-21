import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICherche, Cherche } from 'app/shared/model/cherche.model';
import { ChercheService } from './cherche.service';
import { ChercheComponent } from './cherche.component';
import { ChercheDetailComponent } from './cherche-detail.component';
import { ChercheUpdateComponent } from './cherche-update.component';

@Injectable({ providedIn: 'root' })
export class ChercheResolve implements Resolve<ICherche> {
  constructor(private service: ChercheService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICherche> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((cherche: HttpResponse<Cherche>) => {
          if (cherche.body) {
            return of(cherche.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Cherche());
  }
}

export const chercheRoute: Routes = [
  {
    path: '',
    component: ChercheComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinesApp.cherche.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ChercheDetailComponent,
    resolve: {
      cherche: ChercheResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinesApp.cherche.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ChercheUpdateComponent,
    resolve: {
      cherche: ChercheResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinesApp.cherche.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ChercheUpdateComponent,
    resolve: {
      cherche: ChercheResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinesApp.cherche.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
