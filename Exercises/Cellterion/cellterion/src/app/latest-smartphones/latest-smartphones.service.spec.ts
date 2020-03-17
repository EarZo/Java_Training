import { TestBed } from '@angular/core/testing';

import { LatestSmartphonesService } from './latest-smartphones.service';

describe('LatestSmartphonesService', () => {
  let service: LatestSmartphonesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LatestSmartphonesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
