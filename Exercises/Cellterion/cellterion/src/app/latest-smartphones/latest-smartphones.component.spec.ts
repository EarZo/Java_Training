import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LatestSmartphonesComponent } from './latest-smartphones.component';

describe('LatestSmartphonesComponent', () => {
  let component: LatestSmartphonesComponent;
  let fixture: ComponentFixture<LatestSmartphonesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LatestSmartphonesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LatestSmartphonesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
