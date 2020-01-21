import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IShipping } from 'app/shared/model/shipping.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { ShippingService } from './shipping.service';
import { ShippingDeleteDialogComponent } from './shipping-delete-dialog.component';

@Component({
  selector: 'jhi-shipping',
  templateUrl: './shipping.component.html'
})
export class ShippingComponent implements OnInit, OnDestroy {
  shippings: IShipping[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected shippingService: ShippingService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.shippings = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.shippingService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IShipping[]>) => this.paginateShippings(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.shippings = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInShippings();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IShipping): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInShippings(): void {
    this.eventSubscriber = this.eventManager.subscribe('shippingListModification', () => this.reset());
  }

  delete(shipping: IShipping): void {
    const modalRef = this.modalService.open(ShippingDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.shipping = shipping;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateShippings(data: IShipping[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.shippings.push(data[i]);
      }
    }
  }
}
