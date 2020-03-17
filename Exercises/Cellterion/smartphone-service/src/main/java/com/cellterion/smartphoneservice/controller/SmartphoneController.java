package com.cellterion.smartphoneservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cellterion.smartphoneservice.model.Review;
import com.cellterion.smartphoneservice.model.Smartphone;
import com.cellterion.smartphoneservice.service.SmartphoneService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@RestController
@RequestMapping("/services")
public class SmartphoneController {

	@Autowired
	SmartphoneService smartphoneService;

	@GetMapping("/hello")
	public String sayHello() {
		return "Hello from Smartphone-Service!";
	}

	@PostMapping("/smartphone")
	public Smartphone saveSmartphone(@RequestBody Smartphone smartphone) {
		if (smartphone.getReviews() != null)
			for (Review review : smartphone.getReviews())
				review.setSmartphone(smartphone);
		
		return smartphoneService.saveSmartphone(smartphone);
	}

	@GetMapping("/smartphones")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Smartphone> getAllSmartphones() {
		return smartphoneService.findAllSmartphones();
	}

	@GetMapping("/smartphone/{id}")
	public Smartphone getStudent(@PathVariable Integer id) {
		return smartphoneService.findSmartphoneById(id);
	}

}
