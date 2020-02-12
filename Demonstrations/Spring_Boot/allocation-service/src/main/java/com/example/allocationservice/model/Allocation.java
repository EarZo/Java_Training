package com.example.allocationservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
public @Data class Allocation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer allocationId;
	
	Integer studentId;
	String allocationName;
}
