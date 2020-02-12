import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiDataUtils } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBookingdetails } from 'app/shared/model/bookingdetails.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { BookingdetailsService } from './bookingdetails.service';
import { BookingdetailsDeleteDialogComponent } from './bookingdetails-delete-dialog.component';

@Component({
  selector: 'jhi-bookingdetails',
  templateUrl: './bookingdetails.component.html'
})
export class BookingdetailsComponent implements OnInit, OnDestroy {
  bookingdetails: IBookingdetails[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected bookingdetailsService: BookingdetailsService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.bookingdetails = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.bookingdetailsService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IBookingdetails[]>) => this.paginateBookingdetails(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.bookingdetails = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInBookingdetails();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IBookingdetails): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    return this.dataUtils.openFile(contentType, base64String);
  }

  registerChangeInBookingdetails(): void {
    this.eventSubscriber = this.eventManager.subscribe('bookingdetailsListModification', () => this.reset());
  }

  delete(bookingdetails: IBookingdetails): void {
    const modalRef = this.modalService.open(BookingdetailsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.bookingdetails = bookingdetails;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateBookingdetails(data: IBookingdetails[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.bookingdetails.push(data[i]);
      }
    }
  }
}
