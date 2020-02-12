package com.example.studentservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
public @Data class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer addressId;
	
	String city;
}
