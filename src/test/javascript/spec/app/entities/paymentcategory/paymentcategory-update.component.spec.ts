import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EbusinesTestModule } from '../../../test.module';
import { PaymentcategoryUpdateComponent } from 'app/entities/paymentcategory/paymentcategory-update.component';
import { PaymentcategoryService } from 'app/entities/paymentcategory/paymentcategory.service';
import { Paymentcategory } from 'app/shared/model/paymentcategory.model';

describe('Component Tests', () => {
  describe('Paymentcategory Management Update Component', () => {
    let comp: PaymentcategoryUpdateComponent;
    let fixture: ComponentFixture<PaymentcategoryUpdateComponent>;
    let service: PaymentcategoryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EbusinesTestModule],
        declarations: [PaymentcategoryUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(PaymentcategoryUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PaymentcategoryUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PaymentcategoryService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Paymentcategory(123);
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
        const entity = new Paymentcategory();
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
