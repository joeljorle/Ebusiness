import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EbusinessTestModule } from '../../../test.module';
import { FilesDetailComponent } from 'app/entities/files/files-detail.component';
import { Files } from 'app/shared/model/files.model';

describe('Component Tests', () => {
  describe('Files Management Detail Component', () => {
    let comp: FilesDetailComponent;
    let fixture: ComponentFixture<FilesDetailComponent>;
    const route = ({ data: of({ files: new Files(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EbusinessTestModule],
        declarations: [FilesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(FilesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FilesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load files on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.files).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
