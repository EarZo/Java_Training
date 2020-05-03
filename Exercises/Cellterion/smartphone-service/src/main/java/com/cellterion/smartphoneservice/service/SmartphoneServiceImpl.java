package com.cellterion.smartphoneservice.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.cellterion.smartphoneservice.model.Smartphone;
import com.cellterion.smartphoneservice.repository.SmartphoneRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class SmartphoneServiceImpl implements SmartphoneService {
	
	@Autowired
	SmartphoneRepository smartphoneRepository;

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	RestTemplate restTemplate;

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Override
	public Smartphone saveSmartphone(Smartphone smartphone) {
		return smartphoneRepository.save(smartphone);
	}
	
	@Override
	public Smartphone findSmartphoneById(Integer smartphoneId) {
		Optional<Smartphone> smartphoneOptional = smartphoneRepository.findById(smartphoneId);
		
		if(smartphoneOptional.isPresent())
			return smartphoneOptional.get();
		return null;
	}
	
	@Override
	public List<Smartphone> findAllSmartphones(){
		return smartphoneRepository.findAll();
	}

	@Override
	public List<Smartphone> findLatestSmartphones(Integer manufactureYear){
		return smartphoneRepository.findSmartphonesByManufactureYear(manufactureYear);
	}

	@Override
	public Smartphone[] getSmartphonesByBrandName(String brandName){

		Smartphone smartphone = new Smartphone();
		smartphone.setBrandName(brandName);

		Example<Smartphone> exampleObject = Example.of(smartphone);

		List<Smartphone> smartphones = smartphoneRepository.findAll(exampleObject);

		return smartphones.toArray(new Smartphone[smartphones.size()]);
	}

	@Override
	public List<Smartphone> getSmartphonesByUserBudget(BigDecimal userBudget){
		return entityManager.createQuery
				("SELECT DISTINCT sd.smartphone FROM SmartphoneDealer sd WHERE sd.price <= :userBudget", Smartphone.class)
				.setParameter("userBudget", userBudget).getResultList();
	}
}
