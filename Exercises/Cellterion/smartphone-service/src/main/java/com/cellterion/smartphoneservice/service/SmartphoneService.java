package com.cellterion.smartphoneservice.service;

import java.math.BigDecimal;
import java.util.List;

import com.cellterion.smartphoneservice.model.Smartphone;

public interface SmartphoneService {

	Smartphone saveSmartphone(Smartphone smartphone);

	Smartphone findSmartphoneById(Integer smartphoneId);

	List<Smartphone> findAllSmartphones();

	List<Smartphone> findLatestSmartphones(Integer manufactureYear);

	Smartphone[] getSmartphonesByBrandName(String brandName);

	List<Smartphone> getSmartphonesByUserBudget(BigDecimal userBudget);

}