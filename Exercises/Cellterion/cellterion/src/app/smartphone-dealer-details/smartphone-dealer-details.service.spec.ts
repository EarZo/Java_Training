import { TestBed } from '@angular/core/testing';

import { SmartphoneDealerDetailsService } from './smartphone-dealer-details.service';

describe('SmartphoneDealerDetailsService', () => {
  let service: SmartphoneDealerDetailsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SmartphoneDealerDetailsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
