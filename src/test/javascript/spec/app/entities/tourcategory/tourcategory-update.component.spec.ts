import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EbusinessTestModule } from '../../../test.module';
import { TourcategoryUpdateComponent } from 'app/entities/tourcategory/tourcategory-update.component';
import { TourcategoryService } from 'app/entities/tourcategory/tourcategory.service';
import { Tourcategory } from 'app/shared/model/tourcategory.model';

describe('Component Tests', () => {
  describe('Tourcategory Management Update Component', () => {
    let comp: TourcategoryUpdateComponent;
    let fixture: ComponentFixture<TourcategoryUpdateComponent>;
    let service: TourcategoryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EbusinessTestModule],
        declarations: [TourcategoryUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(TourcategoryUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TourcategoryUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TourcategoryService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Tourcategory(123);
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
        const entity = new Tourcategory();
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
