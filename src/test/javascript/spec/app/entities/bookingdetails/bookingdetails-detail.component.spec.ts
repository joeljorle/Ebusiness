import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { EbusinessTestModule } from '../../../test.module';
import { BookingdetailsDetailComponent } from 'app/entities/bookingdetails/bookingdetails-detail.component';
import { Bookingdetails } from 'app/shared/model/bookingdetails.model';

describe('Component Tests', () => {
  describe('Bookingdetails Management Detail Component', () => {
    let comp: BookingdetailsDetailComponent;
    let fixture: ComponentFixture<BookingdetailsDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ bookingdetails: new Bookingdetails(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EbusinessTestModule],
        declarations: [BookingdetailsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(BookingdetailsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BookingdetailsDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load bookingdetails on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bookingdetails).toEqual(jasmine.objectContaining({ id: 123 }));
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
