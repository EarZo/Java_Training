import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TopSmartphoneDealersComponent } from './top-smartphone-dealers.component';

describe('TopSmartphoneDealersComponent', () => {
  let component: TopSmartphoneDealersComponent;
  let fixture: ComponentFixture<TopSmartphoneDealersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TopSmartphoneDealersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TopSmartphoneDealersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
