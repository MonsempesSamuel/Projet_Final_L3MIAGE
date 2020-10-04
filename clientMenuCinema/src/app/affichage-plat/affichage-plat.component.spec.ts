import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AffichagePlatComponent } from './affichage-plat.component';

describe('AffichagePlatComponent', () => {
  let component: AffichagePlatComponent;
  let fixture: ComponentFixture<AffichagePlatComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AffichagePlatComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AffichagePlatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
