package com.cellterion.smartphoneservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cellterion.smartphoneservice.model.Review;
import com.cellterion.smartphoneservice.model.Smartphone;
import com.cellterion.smartphoneservice.service.SmartphoneService;

@RestController
@RequestMapping("/services")
public class SmartphoneController {

	@Autowired
	SmartphoneService smartphoneService;

	@GetMapping("/hello")
	public String sayHello() {
		return "Hello from SmartphoneController!";
	}

	@GetMapping("/smartphones")
	public List<Smartphone> getAllSmartphones() {
		return smartphoneService.findAllSmartphones();
	}

	@GetMapping("/smartphone/{id}")
	public Smartphone getStudent(@PathVariable Integer id) {
		return smartphoneService.findSmartphoneById(id);
	}

	@PostMapping("/smartphone")
	public Smartphone saveSmartphone(@RequestBody Smartphone smartphone) {
		if (smartphone.getReviews() != null)
			for (Review review : smartphone.getReviews())
				review.setSmartphone(smartphone);
		
		return smartphoneService.saveSmartphone(smartphone);
	}
	
}
