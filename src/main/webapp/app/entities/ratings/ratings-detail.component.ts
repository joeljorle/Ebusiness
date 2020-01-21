import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRatings } from 'app/shared/model/ratings.model';

@Component({
  selector: 'jhi-ratings-detail',
  templateUrl: './ratings-detail.component.html'
})
export class RatingsDetailComponent implements OnInit {
  ratings: IRatings | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ratings }) => {
      this.ratings = ratings;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
