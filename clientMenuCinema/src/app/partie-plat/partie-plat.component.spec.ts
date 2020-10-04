import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PartiePlatComponent } from './partie-plat.component';

describe('PartiePlatComponent', () => {
  let component: PartiePlatComponent;
  let fixture: ComponentFixture<PartiePlatComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PartiePlatComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PartiePlatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
