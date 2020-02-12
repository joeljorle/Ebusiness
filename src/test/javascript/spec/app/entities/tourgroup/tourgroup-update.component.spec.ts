import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EBusinessTestModule } from '../../../test.module';
import { TourgroupUpdateComponent } from 'app/entities/tourgroup/tourgroup-update.component';
import { TourgroupService } from 'app/entities/tourgroup/tourgroup.service';
import { Tourgroup } from 'app/shared/model/tourgroup.model';

describe('Component Tests', () => {
  describe('Tourgroup Management Update Component', () => {
    let comp: TourgroupUpdateComponent;
    let fixture: ComponentFixture<TourgroupUpdateComponent>;
    let service: TourgroupService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EBusinessTestModule],
        declarations: [TourgroupUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(TourgroupUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TourgroupUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TourgroupService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Tourgroup(123);
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
        const entity = new Tourgroup();
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
