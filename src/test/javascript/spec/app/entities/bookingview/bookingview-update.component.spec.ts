import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EbusinessTestModule } from '../../../test.module';
import { BookingviewUpdateComponent } from 'app/entities/bookingview/bookingview-update.component';
import { BookingviewService } from 'app/entities/bookingview/bookingview.service';
import { Bookingview } from 'app/shared/model/bookingview.model';

describe('Component Tests', () => {
  describe('Bookingview Management Update Component', () => {
    let comp: BookingviewUpdateComponent;
    let fixture: ComponentFixture<BookingviewUpdateComponent>;
    let service: BookingviewService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EbusinessTestModule],
        declarations: [BookingviewUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(BookingviewUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BookingviewUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BookingviewService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Bookingview(123);
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
        const entity = new Bookingview();
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
