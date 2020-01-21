import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICherche } from 'app/shared/model/cherche.model';

@Component({
  selector: 'jhi-cherche-detail',
  templateUrl: './cherche-detail.component.html'
})
export class ChercheDetailComponent implements OnInit {
  cherche: ICherche | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cherche }) => {
      this.cherche = cherche;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
