import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PartieFilmComponent } from './partie-film.component';

describe('PartieFilmComponent', () => {
  let component: PartieFilmComponent;
  let fixture: ComponentFixture<PartieFilmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PartieFilmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PartieFilmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
