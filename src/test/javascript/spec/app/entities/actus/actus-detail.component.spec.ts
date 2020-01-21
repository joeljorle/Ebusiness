import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { EbusinesTestModule } from '../../../test.module';
import { ActusDetailComponent } from 'app/entities/actus/actus-detail.component';
import { Actus } from 'app/shared/model/actus.model';

describe('Component Tests', () => {
  describe('Actus Management Detail Component', () => {
    let comp: ActusDetailComponent;
    let fixture: ComponentFixture<ActusDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ actus: new Actus(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EbusinesTestModule],
        declarations: [ActusDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ActusDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ActusDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load actus on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.actus).toEqual(jasmine.objectContaining({ id: 123 }));
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
