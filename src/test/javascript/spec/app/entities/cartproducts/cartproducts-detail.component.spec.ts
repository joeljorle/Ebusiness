import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EbusinessTestModule } from '../../../test.module';
import { CartproductsDetailComponent } from 'app/entities/cartproducts/cartproducts-detail.component';
import { Cartproducts } from 'app/shared/model/cartproducts.model';

describe('Component Tests', () => {
  describe('Cartproducts Management Detail Component', () => {
    let comp: CartproductsDetailComponent;
    let fixture: ComponentFixture<CartproductsDetailComponent>;
    const route = ({ data: of({ cartproducts: new Cartproducts(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EbusinessTestModule],
        declarations: [CartproductsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CartproductsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CartproductsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load cartproducts on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.cartproducts).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
