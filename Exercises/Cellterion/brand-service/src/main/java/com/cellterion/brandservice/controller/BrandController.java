package com.cellterion.brandservice.controller;

import com.cellterion.brandservice.model.Brand;
import com.cellterion.brandservice.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class BrandController {

    @Autowired
    BrandService brandService;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from Brand-Service!";
    }

    @PostMapping("/brand")
    public Brand saveBrand(@RequestBody Brand brand) {
        return brandService.saveBrand(brand);
    }

    @GetMapping("/brands")
    public List<Brand> getAllBrands() {
        return brandService.findAllBrands();
    }

    @GetMapping("/brands/models")
    public List<Brand> getAllBrandModels() {
        List<Brand> brands = brandService.findAllBrands();

        for(Brand brand : brands){
            brand.setSmartphones(brandService.getSmartphones(brand));
        }

        return brands;
    }

    @GetMapping("/brand/{id}")
    public ResponseEntity<Brand> getSmartphone(@PathVariable Integer id) {
        Brand brand = brandService.findBrandById(id);

        if(brand == null){
            return ResponseEntity.notFound().build();
        }else{
            brand.setSmartphones(brandService.getSmartphones(brand));
            return ResponseEntity.ok().body(brand);
        }
    }

}
