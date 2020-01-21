import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiDataUtils } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITourgroup } from 'app/shared/model/tourgroup.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { TourgroupService } from './tourgroup.service';
import { TourgroupDeleteDialogComponent } from './tourgroup-delete-dialog.component';

@Component({
  selector: 'jhi-tourgroup',
  templateUrl: './tourgroup.component.html'
})
export class TourgroupComponent implements OnInit, OnDestroy {
  tourgroups: ITourgroup[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected tourgroupService: TourgroupService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.tourgroups = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.tourgroupService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<ITourgroup[]>) => this.paginateTourgroups(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.tourgroups = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTourgroups();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITourgroup): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    return this.dataUtils.openFile(contentType, base64String);
  }

  registerChangeInTourgroups(): void {
    this.eventSubscriber = this.eventManager.subscribe('tourgroupListModification', () => this.reset());
  }

  delete(tourgroup: ITourgroup): void {
    const modalRef = this.modalService.open(TourgroupDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tourgroup = tourgroup;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateTourgroups(data: ITourgroup[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.tourgroups.push(data[i]);
      }
    }
  }
}
