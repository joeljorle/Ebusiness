import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { EBusinessTestModule } from '../../../test.module';
import { PaymentmethodDetailComponent } from 'app/entities/paymentmethod/paymentmethod-detail.component';
import { Paymentmethod } from 'app/shared/model/paymentmethod.model';

describe('Component Tests', () => {
  describe('Paymentmethod Management Detail Component', () => {
    let comp: PaymentmethodDetailComponent;
    let fixture: ComponentFixture<PaymentmethodDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ paymentmethod: new Paymentmethod(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EBusinessTestModule],
        declarations: [PaymentmethodDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PaymentmethodDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PaymentmethodDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load paymentmethod on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.paymentmethod).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });

    describe('byteSize', () => {
      it('Should call byteSize from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'byteSize');
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.byteSize(fakeBase64);

        // THEN
        expect(dataUtils.byteSize).toBeCalledWith(fakeBase64);
      });
    });

    describe('openFile', () => {
      it('Should call openFile from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'openFile');
        const fakeContentType = 'fake content type';
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.openFile(fakeContentType, fakeBase64);

        // THEN
        expect(dataUtils.openFile).toBeCalledWith(fakeContentType, fakeBase64);
      });
    });
  });
});
