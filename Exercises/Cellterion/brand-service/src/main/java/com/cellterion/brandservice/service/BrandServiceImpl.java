package com.cellterion.brandservice.service;

import com.cellterion.brandservice.breaker.SmartphoneCommand;
import com.cellterion.brandservice.model.Brand;
import com.cellterion.brandservice.repository.BrandRepository;
import com.cellterion.brandservice.sharedModel.Smartphone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Override
    public Brand saveBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Brand findBrandById(Integer brandId) {
        Optional<Brand> brandOptional = brandRepository.findById(brandId);

        if(brandOptional.isPresent())
            return brandOptional.get();
        return null;
    }

    @Override
    public List<Brand> findAllBrands(){
        return brandRepository.findAll();
    }

    public Smartphone[] getSmartphones(Brand brand){
        HttpHeaders httpHeaders = new HttpHeaders();

        SmartphoneCommand allocationCommand = new SmartphoneCommand(brand, httpHeaders, restTemplate);

        return allocationCommand.execute();
    }

}
