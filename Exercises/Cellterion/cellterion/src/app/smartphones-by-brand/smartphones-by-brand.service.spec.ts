import { TestBed } from '@angular/core/testing';

import { SmartphonesByBrandService } from './smartphones-by-brand.service';

describe('SmartphonesByBrandService', () => {
  let service: SmartphonesByBrandService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SmartphonesByBrandService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
