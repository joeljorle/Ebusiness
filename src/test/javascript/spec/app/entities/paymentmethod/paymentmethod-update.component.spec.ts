import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EbusinessTestModule } from '../../../test.module';
import { PaymentmethodUpdateComponent } from 'app/entities/paymentmethod/paymentmethod-update.component';
import { PaymentmethodService } from 'app/entities/paymentmethod/paymentmethod.service';
import { Paymentmethod } from 'app/shared/model/paymentmethod.model';

describe('Component Tests', () => {
  describe('Paymentmethod Management Update Component', () => {
    let comp: PaymentmethodUpdateComponent;
    let fixture: ComponentFixture<PaymentmethodUpdateComponent>;
    let service: PaymentmethodService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EbusinessTestModule],
        declarations: [PaymentmethodUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(PaymentmethodUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PaymentmethodUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PaymentmethodService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Paymentmethod(123);
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
        const entity = new Paymentmethod();
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
