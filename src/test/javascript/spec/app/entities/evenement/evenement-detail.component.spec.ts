import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { EBusinessTestModule } from '../../../test.module';
import { EvenementDetailComponent } from 'app/entities/evenement/evenement-detail.component';
import { Evenement } from 'app/shared/model/evenement.model';

describe('Component Tests', () => {
  describe('Evenement Management Detail Component', () => {
    let comp: EvenementDetailComponent;
    let fixture: ComponentFixture<EvenementDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ evenement: new Evenement(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EBusinessTestModule],
        declarations: [EvenementDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EvenementDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EvenementDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load evenement on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.evenement).toEqual(jasmine.objectContaining({ id: 123 }));
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
