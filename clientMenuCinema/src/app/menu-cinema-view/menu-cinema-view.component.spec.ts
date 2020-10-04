import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuCinemaViewComponent } from './menu-cinema-view.component';

describe('MenuCinemaViewComponent', () => {
  let component: MenuCinemaViewComponent;
  let fixture: ComponentFixture<MenuCinemaViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MenuCinemaViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MenuCinemaViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
