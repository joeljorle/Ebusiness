import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EbusinesTestModule } from '../../../test.module';
import { ChercheDetailComponent } from 'app/entities/cherche/cherche-detail.component';
import { Cherche } from 'app/shared/model/cherche.model';

describe('Component Tests', () => {
  describe('Cherche Management Detail Component', () => {
    let comp: ChercheDetailComponent;
    let fixture: ComponentFixture<ChercheDetailComponent>;
    const route = ({ data: of({ cherche: new Cherche(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EbusinesTestModule],
        declarations: [ChercheDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ChercheDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ChercheDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load cherche on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.cherche).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
