import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EbusinesTestModule } from '../../../test.module';
import { CategorydetailsUpdateComponent } from 'app/entities/categorydetails/categorydetails-update.component';
import { CategorydetailsService } from 'app/entities/categorydetails/categorydetails.service';
import { Categorydetails } from 'app/shared/model/categorydetails.model';

describe('Component Tests', () => {
  describe('Categorydetails Management Update Component', () => {
    let comp: CategorydetailsUpdateComponent;
    let fixture: ComponentFixture<CategorydetailsUpdateComponent>;
    let service: CategorydetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EbusinesTestModule],
        declarations: [CategorydetailsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CategorydetailsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CategorydetailsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CategorydetailsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Categorydetails(123);
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
        const entity = new Categorydetails();
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
