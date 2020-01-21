import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EbusinesTestModule } from '../../../test.module';
import { RatingsUpdateComponent } from 'app/entities/ratings/ratings-update.component';
import { RatingsService } from 'app/entities/ratings/ratings.service';
import { Ratings } from 'app/shared/model/ratings.model';

describe('Component Tests', () => {
  describe('Ratings Management Update Component', () => {
    let comp: RatingsUpdateComponent;
    let fixture: ComponentFixture<RatingsUpdateComponent>;
    let service: RatingsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EbusinesTestModule],
        declarations: [RatingsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(RatingsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RatingsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RatingsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Ratings(123);
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
        const entity = new Ratings();
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
