package com.example.allocationservice.service;

import java.util.List;

import com.example.allocationservice.model.Allocation;

public interface AllocationService {

	Allocation saveAllocation(Allocation allocation);

	List<Allocation> getAllocationsByStudentId(Integer studentId);

}