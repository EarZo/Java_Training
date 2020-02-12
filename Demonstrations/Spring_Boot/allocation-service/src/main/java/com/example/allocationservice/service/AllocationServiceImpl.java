package com.example.allocationservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.allocationservice.model.Allocation;
import com.example.allocationservice.repository.AllocationRepository;

@Service
public class AllocationServiceImpl implements AllocationService {
	
	@Autowired
	AllocationRepository allocationRepository;

	@Override
	public Allocation saveAllocation(Allocation allocation) {
		return allocationRepository.save(allocation);
	}
	
	@Override
	public List<Allocation> getAllocationsByStudentId(Integer studentId){
		
		Allocation allocation = new Allocation();
		allocation.setStudentId(studentId);
		
		Example<Allocation> exampleObject = Example.of(allocation);
		
		return allocationRepository.findAll(exampleObject);
	}
}
