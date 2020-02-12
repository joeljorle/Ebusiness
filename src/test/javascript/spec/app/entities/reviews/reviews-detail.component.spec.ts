import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EBusinessTestModule } from '../../../test.module';
import { ReviewsDetailComponent } from 'app/entities/reviews/reviews-detail.component';
import { Reviews } from 'app/shared/model/reviews.model';

describe('Component Tests', () => {
  describe('Reviews Management Detail Component', () => {
    let comp: ReviewsDetailComponent;
    let fixture: ComponentFixture<ReviewsDetailComponent>;
    const route = ({ data: of({ reviews: new Reviews(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EBusinessTestModule],
        declarations: [ReviewsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ReviewsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ReviewsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load reviews on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.reviews).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
