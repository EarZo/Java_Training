package com.example.allocationservice.service;

import com.example.allocationservice.model.Allocation;

public interface AllocationService {

	Allocation saveAllocation(Allocation allocation);

	Allocation[] getAllocationsByStudentId(Integer studentId);

}