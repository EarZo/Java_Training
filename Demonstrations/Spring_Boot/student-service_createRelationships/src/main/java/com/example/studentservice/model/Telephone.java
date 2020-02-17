package com.example.studentservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
public @Data class Telephone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer tid;

	String telephoneNumber;

	@ManyToOne
	@JoinColumn(name = "student_id")
//	@JsonIgnore
	Student student;
}
