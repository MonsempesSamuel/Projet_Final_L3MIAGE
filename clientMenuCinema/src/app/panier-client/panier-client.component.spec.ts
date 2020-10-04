import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PanierClientComponent } from './panier-client.component';

describe('PanierClientComponent', () => {
  let component: PanierClientComponent;
  let fixture: ComponentFixture<PanierClientComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PanierClientComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PanierClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
