package com.cellterion.brandservice.breaker;

import com.cellterion.brandservice.model.Brand;
import com.cellterion.brandservice.sharedModel.Smartphone;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SmartphoneCommand extends HystrixCommand<Smartphone[]> {

    Brand brand;
    HttpHeaders httpHeaders;
    RestTemplate restTemplate;

    public SmartphoneCommand(Brand brand, HttpHeaders httpHeaders, RestTemplate restTemplate) {
        super(HystrixCommandGroupKey.Factory.asKey("default"));
        this.brand = brand;
        this.httpHeaders = httpHeaders;
        this.restTemplate = restTemplate;
    }

    @Override
    protected Smartphone[] run() throws Exception {
        HttpEntity<String> smartphonesRequest = new HttpEntity<>("", httpHeaders);

        ResponseEntity<Smartphone[]> smartphonesResponse = restTemplate.exchange("http://smartphoneService/services/smartphone/all/brand/" + brand.getBrandName(), HttpMethod.GET, smartphonesRequest, Smartphone[].class);

        return smartphonesResponse.getBody();
    }

    @Override
    protected Smartphone[] getFallback() {
        return new Smartphone[1];
    }

}
