import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ValidePanierComponent } from './valide-panier.component';

describe('ValidePanierComponent', () => {
  let component: ValidePanierComponent;
  let fixture: ComponentFixture<ValidePanierComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ValidePanierComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ValidePanierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
