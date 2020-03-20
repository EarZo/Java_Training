import { TestBed } from '@angular/core/testing';

import { SmartphoneDetailsService } from './smartphone-details.service';

describe('SmartphoneDetailsService', () => {
  let service: SmartphoneDetailsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SmartphoneDetailsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
