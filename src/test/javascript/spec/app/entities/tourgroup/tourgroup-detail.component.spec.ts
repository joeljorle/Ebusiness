import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { EbusinessTestModule } from '../../../test.module';
import { TourgroupDetailComponent } from 'app/entities/tourgroup/tourgroup-detail.component';
import { Tourgroup } from 'app/shared/model/tourgroup.model';

describe('Component Tests', () => {
  describe('Tourgroup Management Detail Component', () => {
    let comp: TourgroupDetailComponent;
    let fixture: ComponentFixture<TourgroupDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ tourgroup: new Tourgroup(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EbusinessTestModule],
        declarations: [TourgroupDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(TourgroupDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TourgroupDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load tourgroup on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tourgroup).toEqual(jasmine.objectContaining({ id: 123 }));
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
