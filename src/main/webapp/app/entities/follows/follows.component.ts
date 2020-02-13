import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFollows } from 'app/shared/model/follows.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { FollowsService } from './follows.service';
import { FollowsDeleteDialogComponent } from './follows-delete-dialog.component';

@Component({
  selector: 'jhi-follows',
  templateUrl: './follows.component.html'
})
export class FollowsComponent implements OnInit, OnDestroy {
  follows: IFollows[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected followsService: FollowsService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.follows = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.followsService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IFollows[]>) => this.paginateFollows(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.follows = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInFollows();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IFollows): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInFollows(): void {
    this.eventSubscriber = this.eventManager.subscribe('followsListModification', () => this.reset());
  }

  delete(follows: IFollows): void {
    const modalRef = this.modalService.open(FollowsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.follows = follows;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateFollows(data: IFollows[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.follows.push(data[i]);
      }
    }
  }
}
