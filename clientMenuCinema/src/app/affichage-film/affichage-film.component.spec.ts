import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AffichageFilmComponent } from './affichage-film.component';

describe('AffichageFilmComponent', () => {
  let component: AffichageFilmComponent;
  let fixture: ComponentFixture<AffichageFilmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AffichageFilmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AffichageFilmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
