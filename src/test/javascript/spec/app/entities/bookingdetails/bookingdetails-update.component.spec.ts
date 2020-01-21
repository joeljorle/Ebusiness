import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EbusinesTestModule } from '../../../test.module';
import { BookingdetailsUpdateComponent } from 'app/entities/bookingdetails/bookingdetails-update.component';
import { BookingdetailsService } from 'app/entities/bookingdetails/bookingdetails.service';
import { Bookingdetails } from 'app/shared/model/bookingdetails.model';

describe('Component Tests', () => {
  describe('Bookingdetails Management Update Component', () => {
    let comp: BookingdetailsUpdateComponent;
    let fixture: ComponentFixture<BookingdetailsUpdateComponent>;
    let service: BookingdetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EbusinesTestModule],
        declarations: [BookingdetailsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(BookingdetailsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BookingdetailsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BookingdetailsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Bookingdetails(123);
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
        const entity = new Bookingdetails();
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
