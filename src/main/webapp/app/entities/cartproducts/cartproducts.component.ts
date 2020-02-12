import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICartproducts } from 'app/shared/model/cartproducts.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { CartproductsService } from './cartproducts.service';
import { CartproductsDeleteDialogComponent } from './cartproducts-delete-dialog.component';

@Component({
  selector: 'jhi-cartproducts',
  templateUrl: './cartproducts.component.html'
})
export class CartproductsComponent implements OnInit, OnDestroy {
  cartproducts: ICartproducts[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected cartproductsService: CartproductsService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.cartproducts = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.cartproductsService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<ICartproducts[]>) => this.paginateCartproducts(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.cartproducts = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCartproducts();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICartproducts): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCartproducts(): void {
    this.eventSubscriber = this.eventManager.subscribe('cartproductsListModification', () => this.reset());
  }

  delete(cartproducts: ICartproducts): void {
    const modalRef = this.modalService.open(CartproductsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.cartproducts = cartproducts;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateCartproducts(data: ICartproducts[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.cartproducts.push(data[i]);
      }
    }
  }
}
