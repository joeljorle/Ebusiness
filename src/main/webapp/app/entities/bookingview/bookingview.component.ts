import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiDataUtils } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBookingview } from 'app/shared/model/bookingview.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { BookingviewService } from './bookingview.service';
import { BookingviewDeleteDialogComponent } from './bookingview-delete-dialog.component';

@Component({
  selector: 'jhi-bookingview',
  templateUrl: './bookingview.component.html'
})
export class BookingviewComponent implements OnInit, OnDestroy {
  bookingviews: IBookingview[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected bookingviewService: BookingviewService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.bookingviews = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.bookingviewService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IBookingview[]>) => this.paginateBookingviews(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.bookingviews = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInBookingviews();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IBookingview): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    return this.dataUtils.openFile(contentType, base64String);
  }

  registerChangeInBookingviews(): void {
    this.eventSubscriber = this.eventManager.subscribe('bookingviewListModification', () => this.reset());
  }

  delete(bookingview: IBookingview): void {
    const modalRef = this.modalService.open(BookingviewDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.bookingview = bookingview;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateBookingviews(data: IBookingview[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.bookingviews.push(data[i]);
      }
    }
  }
}
