import { TestBed } from '@angular/core/testing';

import { FeaturedBrandsService } from './featured-brands.service';

describe('FeaturedBrandsService', () => {
  let service: FeaturedBrandsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FeaturedBrandsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
