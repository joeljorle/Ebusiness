import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IRatings } from 'app/shared/model/ratings.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { RatingsService } from './ratings.service';
import { RatingsDeleteDialogComponent } from './ratings-delete-dialog.component';

@Component({
  selector: 'jhi-ratings',
  templateUrl: './ratings.component.html'
})
export class RatingsComponent implements OnInit, OnDestroy {
  ratings: IRatings[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected ratingsService: RatingsService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.ratings = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.ratingsService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IRatings[]>) => this.paginateRatings(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.ratings = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInRatings();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IRatings): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInRatings(): void {
    this.eventSubscriber = this.eventManager.subscribe('ratingsListModification', () => this.reset());
  }

  delete(ratings: IRatings): void {
    const modalRef = this.modalService.open(RatingsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ratings = ratings;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateRatings(data: IRatings[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.ratings.push(data[i]);
      }
    }
  }
}
