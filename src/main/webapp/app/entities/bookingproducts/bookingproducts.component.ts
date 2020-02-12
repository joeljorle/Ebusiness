import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBookingproducts } from 'app/shared/model/bookingproducts.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { BookingproductsService } from './bookingproducts.service';
import { BookingproductsDeleteDialogComponent } from './bookingproducts-delete-dialog.component';

@Component({
  selector: 'jhi-bookingproducts',
  templateUrl: './bookingproducts.component.html'
})
export class BookingproductsComponent implements OnInit, OnDestroy {
  bookingproducts: IBookingproducts[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected bookingproductsService: BookingproductsService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.bookingproducts = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.bookingproductsService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IBookingproducts[]>) => this.paginateBookingproducts(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.bookingproducts = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInBookingproducts();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IBookingproducts): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInBookingproducts(): void {
    this.eventSubscriber = this.eventManager.subscribe('bookingproductsListModification', () => this.reset());
  }

  delete(bookingproducts: IBookingproducts): void {
    const modalRef = this.modalService.open(BookingproductsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.bookingproducts = bookingproducts;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateBookingproducts(data: IBookingproducts[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.bookingproducts.push(data[i]);
      }
    }
  }
}
