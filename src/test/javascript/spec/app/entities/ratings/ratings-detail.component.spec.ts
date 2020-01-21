import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EbusinesTestModule } from '../../../test.module';
import { RatingsDetailComponent } from 'app/entities/ratings/ratings-detail.component';
import { Ratings } from 'app/shared/model/ratings.model';

describe('Component Tests', () => {
  describe('Ratings Management Detail Component', () => {
    let comp: RatingsDetailComponent;
    let fixture: ComponentFixture<RatingsDetailComponent>;
    const route = ({ data: of({ ratings: new Ratings(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EbusinesTestModule],
        declarations: [RatingsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(RatingsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RatingsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ratings on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ratings).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
