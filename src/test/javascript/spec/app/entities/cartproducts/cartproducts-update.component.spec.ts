import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EbusinesTestModule } from '../../../test.module';
import { CartproductsUpdateComponent } from 'app/entities/cartproducts/cartproducts-update.component';
import { CartproductsService } from 'app/entities/cartproducts/cartproducts.service';
import { Cartproducts } from 'app/shared/model/cartproducts.model';

describe('Component Tests', () => {
  describe('Cartproducts Management Update Component', () => {
    let comp: CartproductsUpdateComponent;
    let fixture: ComponentFixture<CartproductsUpdateComponent>;
    let service: CartproductsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EbusinesTestModule],
        declarations: [CartproductsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CartproductsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CartproductsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CartproductsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Cartproducts(123);
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
        const entity = new Cartproducts();
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
