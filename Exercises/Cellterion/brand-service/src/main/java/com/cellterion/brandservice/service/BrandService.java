package com.cellterion.brandservice.service;

import com.cellterion.brandservice.model.Brand;
import com.cellterion.brandservice.sharedModel.Smartphone;

import java.util.List;

public interface BrandService {
    Brand saveBrand(Brand brand);

    Brand findBrandById(Integer brandId);

    List<Brand> findAllBrands();

    Smartphone[] getSmartphones(Brand brand);
}
