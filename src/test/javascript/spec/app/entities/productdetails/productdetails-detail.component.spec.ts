import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { EbusinessTestModule } from '../../../test.module';
import { ProductdetailsDetailComponent } from 'app/entities/productdetails/productdetails-detail.component';
import { Productdetails } from 'app/shared/model/productdetails.model';

describe('Component Tests', () => {
  describe('Productdetails Management Detail Component', () => {
    let comp: ProductdetailsDetailComponent;
    let fixture: ComponentFixture<ProductdetailsDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ productdetails: new Productdetails(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EbusinessTestModule],
        declarations: [ProductdetailsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProductdetailsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProductdetailsDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load productdetails on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.productdetails).toEqual(jasmine.objectContaining({ id: 123 }));
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
