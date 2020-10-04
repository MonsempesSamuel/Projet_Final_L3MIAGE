import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdresseClientComponent } from './adresse-client.component';

describe('AdresseClientComponent', () => {
  let component: AdresseClientComponent;
  let fixture: ComponentFixture<AdresseClientComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdresseClientComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdresseClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
