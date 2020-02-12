package com.example.allocationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.allocationservice.model.Allocation;

public interface AllocationRepository extends JpaRepository<Allocation, Integer> {

}
