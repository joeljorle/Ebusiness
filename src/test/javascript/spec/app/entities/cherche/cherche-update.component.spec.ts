import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EbusinesTestModule } from '../../../test.module';
import { ChercheUpdateComponent } from 'app/entities/cherche/cherche-update.component';
import { ChercheService } from 'app/entities/cherche/cherche.service';
import { Cherche } from 'app/shared/model/cherche.model';

describe('Component Tests', () => {
  describe('Cherche Management Update Component', () => {
    let comp: ChercheUpdateComponent;
    let fixture: ComponentFixture<ChercheUpdateComponent>;
    let service: ChercheService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EbusinesTestModule],
        declarations: [ChercheUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ChercheUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ChercheUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ChercheService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Cherche(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Cherche();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
