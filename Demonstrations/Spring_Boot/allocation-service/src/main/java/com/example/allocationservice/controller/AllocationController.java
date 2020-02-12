package com.example.allocationservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.allocationservice.model.Allocation;
import com.example.allocationservice.service.AllocationService;

@RestController
@RequestMapping("/services")
public class AllocationController {

	@Autowired
	AllocationService allocationService;
	
	@PostMapping("/allocation")
	public Allocation saveAllocation(@RequestBody Allocation allocation) {
		return allocationService.saveAllocation(allocation);
	}
	
	@GetMapping("/allocation/{studentId}")
	public List<Allocation> getAllocationByStudentId(@PathVariable Integer studentId){
		return allocationService.getAllocationsByStudentId(studentId);
	}
	
}
