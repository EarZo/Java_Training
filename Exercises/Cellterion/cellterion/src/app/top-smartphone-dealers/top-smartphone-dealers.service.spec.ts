import { TestBed } from '@angular/core/testing';

import { TopSmartphoneDealersService } from './top-smartphone-dealers.service';

describe('TopSmartphoneDealersService', () => {
  let service: TopSmartphoneDealersService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TopSmartphoneDealersService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
