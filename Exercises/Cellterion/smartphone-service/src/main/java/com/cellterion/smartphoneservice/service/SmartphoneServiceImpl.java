package com.cellterion.smartphoneservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cellterion.smartphoneservice.model.Smartphone;
import com.cellterion.smartphoneservice.repository.SmartphoneRepository;

@Service
public class SmartphoneServiceImpl implements SmartphoneService {
	
	@Autowired
	SmartphoneRepository smartphoneRepository;
	
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
	
}
