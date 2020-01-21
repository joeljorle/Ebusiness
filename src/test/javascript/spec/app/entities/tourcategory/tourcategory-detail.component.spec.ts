import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { EbusinesTestModule } from '../../../test.module';
import { TourcategoryDetailComponent } from 'app/entities/tourcategory/tourcategory-detail.component';
import { Tourcategory } from 'app/shared/model/tourcategory.model';

describe('Component Tests', () => {
  describe('Tourcategory Management Detail Component', () => {
    let comp: TourcategoryDetailComponent;
    let fixture: ComponentFixture<TourcategoryDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ tourcategory: new Tourcategory(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EbusinesTestModule],
        declarations: [TourcategoryDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(TourcategoryDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TourcategoryDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load tourcategory on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tourcategory).toEqual(jasmine.objectContaining({ id: 123 }));
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
