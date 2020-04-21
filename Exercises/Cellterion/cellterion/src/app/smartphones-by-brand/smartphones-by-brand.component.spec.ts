import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SmartphonesByBrandComponent } from './smartphones-by-brand.component';

describe('SmartphonesByBrandComponent', () => {
  let component: SmartphonesByBrandComponent;
  let fixture: ComponentFixture<SmartphonesByBrandComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SmartphonesByBrandComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SmartphonesByBrandComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
