import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { EBusinessTestModule } from '../../../test.module';
import { BookingviewDetailComponent } from 'app/entities/bookingview/bookingview-detail.component';
import { Bookingview } from 'app/shared/model/bookingview.model';

describe('Component Tests', () => {
  describe('Bookingview Management Detail Component', () => {
    let comp: BookingviewDetailComponent;
    let fixture: ComponentFixture<BookingviewDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ bookingview: new Bookingview(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EBusinessTestModule],
        declarations: [BookingviewDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(BookingviewDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BookingviewDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load bookingview on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bookingview).toEqual(jasmine.objectContaining({ id: 123 }));
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
