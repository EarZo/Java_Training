package com.cellterion.smartphoneservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
public @Data class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer reviewId;
	
	int rating;
	String review;
	
	@ManyToOne
	@JoinColumn(name = "smartphone_id")
	@JsonIgnore
	Smartphone smartphone;
	
}
