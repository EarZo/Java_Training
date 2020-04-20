import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SmartphoneDealerDetailsComponent } from './smartphone-dealer-details.component';

describe('SmartphoneDealerDetailsComponent', () => {
  let component: SmartphoneDealerDetailsComponent;
  let fixture: ComponentFixture<SmartphoneDealerDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SmartphoneDealerDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SmartphoneDealerDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
