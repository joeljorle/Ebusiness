import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITourcategory, Tourcategory } from 'app/shared/model/tourcategory.model';
import { TourcategoryService } from './tourcategory.service';
import { TourcategoryComponent } from './tourcategory.component';
import { TourcategoryDetailComponent } from './tourcategory-detail.component';
import { TourcategoryUpdateComponent } from './tourcategory-update.component';

@Injectable({ providedIn: 'root' })
export class TourcategoryResolve implements Resolve<ITourcategory> {
  constructor(private service: TourcategoryService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITourcategory> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tourcategory: HttpResponse<Tourcategory>) => {
          if (tourcategory.body) {
            return of(tourcategory.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Tourcategory());
  }
}

export const tourcategoryRoute: Routes = [
  {
    path: '',
    component: TourcategoryComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinessApp.tourcategory.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TourcategoryDetailComponent,
    resolve: {
      tourcategory: TourcategoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinessApp.tourcategory.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TourcategoryUpdateComponent,
    resolve: {
      tourcategory: TourcategoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinessApp.tourcategory.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TourcategoryUpdateComponent,
    resolve: {
      tourcategory: TourcategoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinessApp.tourcategory.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
