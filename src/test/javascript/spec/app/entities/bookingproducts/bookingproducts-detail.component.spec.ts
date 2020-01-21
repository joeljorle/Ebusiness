import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EbusinesTestModule } from '../../../test.module';
import { BookingproductsDetailComponent } from 'app/entities/bookingproducts/bookingproducts-detail.component';
import { Bookingproducts } from 'app/shared/model/bookingproducts.model';

describe('Component Tests', () => {
  describe('Bookingproducts Management Detail Component', () => {
    let comp: BookingproductsDetailComponent;
    let fixture: ComponentFixture<BookingproductsDetailComponent>;
    const route = ({ data: of({ bookingproducts: new Bookingproducts(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EbusinesTestModule],
        declarations: [BookingproductsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(BookingproductsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BookingproductsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load bookingproducts on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bookingproducts).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
