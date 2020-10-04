import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RechercheFilmComponent } from './recherche-film.component';

describe('RechercheFilmComponent', () => {
  let component: RechercheFilmComponent;
  let fixture: ComponentFixture<RechercheFilmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RechercheFilmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RechercheFilmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
