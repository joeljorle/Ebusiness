import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EBusinessTestModule } from '../../../test.module';
import { PaymentcategoryDetailComponent } from 'app/entities/paymentcategory/paymentcategory-detail.component';
import { Paymentcategory } from 'app/shared/model/paymentcategory.model';

describe('Component Tests', () => {
  describe('Paymentcategory Management Detail Component', () => {
    let comp: PaymentcategoryDetailComponent;
    let fixture: ComponentFixture<PaymentcategoryDetailComponent>;
    const route = ({ data: of({ paymentcategory: new Paymentcategory(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EBusinessTestModule],
        declarations: [PaymentcategoryDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PaymentcategoryDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PaymentcategoryDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load paymentcategory on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.paymentcategory).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
