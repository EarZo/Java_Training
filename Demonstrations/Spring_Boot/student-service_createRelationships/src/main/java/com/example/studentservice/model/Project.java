package com.example.studentservice.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
public @Data class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer projectId;
	
	String projectName;
	
	@ManyToMany(mappedBy = "projects")
//	@JsonIgnore
	List<Student> students;
}
