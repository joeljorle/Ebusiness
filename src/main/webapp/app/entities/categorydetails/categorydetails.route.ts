import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICategorydetails, Categorydetails } from 'app/shared/model/categorydetails.model';
import { CategorydetailsService } from './categorydetails.service';
import { CategorydetailsComponent } from './categorydetails.component';
import { CategorydetailsDetailComponent } from './categorydetails-detail.component';
import { CategorydetailsUpdateComponent } from './categorydetails-update.component';

@Injectable({ providedIn: 'root' })
export class CategorydetailsResolve implements Resolve<ICategorydetails> {
  constructor(private service: CategorydetailsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICategorydetails> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((categorydetails: HttpResponse<Categorydetails>) => {
          if (categorydetails.body) {
            return of(categorydetails.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Categorydetails());
  }
}

export const categorydetailsRoute: Routes = [
  {
    path: '',
    component: CategorydetailsComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinessApp.categorydetails.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CategorydetailsDetailComponent,
    resolve: {
      categorydetails: CategorydetailsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinessApp.categorydetails.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CategorydetailsUpdateComponent,
    resolve: {
      categorydetails: CategorydetailsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinessApp.categorydetails.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CategorydetailsUpdateComponent,
    resolve: {
      categorydetails: CategorydetailsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ebusinessApp.categorydetails.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
