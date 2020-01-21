import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EbusinesTestModule } from '../../../test.module';
import { BookingproductsUpdateComponent } from 'app/entities/bookingproducts/bookingproducts-update.component';
import { BookingproductsService } from 'app/entities/bookingproducts/bookingproducts.service';
import { Bookingproducts } from 'app/shared/model/bookingproducts.model';

describe('Component Tests', () => {
  describe('Bookingproducts Management Update Component', () => {
    let comp: BookingproductsUpdateComponent;
    let fixture: ComponentFixture<BookingproductsUpdateComponent>;
    let service: BookingproductsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EbusinesTestModule],
        declarations: [BookingproductsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(BookingproductsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BookingproductsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BookingproductsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Bookingproducts(123);
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
        const entity = new Bookingproducts();
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
