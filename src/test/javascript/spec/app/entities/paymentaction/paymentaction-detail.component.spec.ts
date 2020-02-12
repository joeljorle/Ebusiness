import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EBusinessTestModule } from '../../../test.module';
import { PaymentactionDetailComponent } from 'app/entities/paymentaction/paymentaction-detail.component';
import { Paymentaction } from 'app/shared/model/paymentaction.model';

describe('Component Tests', () => {
  describe('Paymentaction Management Detail Component', () => {
    let comp: PaymentactionDetailComponent;
    let fixture: ComponentFixture<PaymentactionDetailComponent>;
    const route = ({ data: of({ paymentaction: new Paymentaction(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EBusinessTestModule],
        declarations: [PaymentactionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PaymentactionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PaymentactionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load paymentaction on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.paymentaction).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
