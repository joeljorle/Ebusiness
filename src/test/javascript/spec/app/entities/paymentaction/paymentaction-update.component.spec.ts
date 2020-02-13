import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EbusinessTestModule } from '../../../test.module';
import { PaymentactionUpdateComponent } from 'app/entities/paymentaction/paymentaction-update.component';
import { PaymentactionService } from 'app/entities/paymentaction/paymentaction.service';
import { Paymentaction } from 'app/shared/model/paymentaction.model';

describe('Component Tests', () => {
  describe('Paymentaction Management Update Component', () => {
    let comp: PaymentactionUpdateComponent;
    let fixture: ComponentFixture<PaymentactionUpdateComponent>;
    let service: PaymentactionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EbusinessTestModule],
        declarations: [PaymentactionUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(PaymentactionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PaymentactionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PaymentactionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Paymentaction(123);
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
        const entity = new Paymentaction();
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
