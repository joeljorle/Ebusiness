import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ILikes } from 'app/shared/model/likes.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { LikesService } from './likes.service';
import { LikesDeleteDialogComponent } from './likes-delete-dialog.component';

@Component({
  selector: 'jhi-likes',
  templateUrl: './likes.component.html'
})
export class LikesComponent implements OnInit, OnDestroy {
  likes: ILikes[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected likesService: LikesService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.likes = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.likesService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<ILikes[]>) => this.paginateLikes(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.likes = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInLikes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ILikes): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInLikes(): void {
    this.eventSubscriber = this.eventManager.subscribe('likesListModification', () => this.reset());
  }

  delete(likes: ILikes): void {
    const modalRef = this.modalService.open(LikesDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.likes = likes;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateLikes(data: ILikes[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.likes.push(data[i]);
      }
    }
  }
}
