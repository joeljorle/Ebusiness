import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICherche } from 'app/shared/model/cherche.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { ChercheService } from './cherche.service';
import { ChercheDeleteDialogComponent } from './cherche-delete-dialog.component';

@Component({
  selector: 'jhi-cherche',
  templateUrl: './cherche.component.html'
})
export class ChercheComponent implements OnInit, OnDestroy {
  cherches: ICherche[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected chercheService: ChercheService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.cherches = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.chercheService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<ICherche[]>) => this.paginateCherches(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.cherches = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCherches();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICherche): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCherches(): void {
    this.eventSubscriber = this.eventManager.subscribe('chercheListModification', () => this.reset());
  }

  delete(cherche: ICherche): void {
    const modalRef = this.modalService.open(ChercheDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.cherche = cherche;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateCherches(data: ICherche[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.cherches.push(data[i]);
      }
    }
  }
}
