package com.example.studentservice.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.example.studentservice.sharedModel.Allocation;

import lombok.Data;

@Entity
public @Data class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer studentId;

	String name;
	int age;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	Address address;

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE) // still eagerly loads the collection, but eliminates the MultipleBagFetchException
	List<Telephone> telephoneNumbers;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "student_project", joinColumns = {
			@JoinColumn(name = "student_id", referencedColumnName = "studentId") }, inverseJoinColumns = {
					@JoinColumn(name = "project_id", referencedColumnName = "projectId") })
	@LazyCollection(LazyCollectionOption.FALSE) // still eagerly loads the collection, but eliminates the MultipleBagFetchException
	List<Project> projects;
	
	@Transient
	Allocation[] studentAllocations;
}
