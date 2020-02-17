package com.example.studentservice.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@LazyCollection(LazyCollectionOption.FALSE) // still eagerly loads the collection, but eliminates the MultipleBagFetchException, i.e. we can have two or more eager loadings at once
	@Fetch(FetchMode.SELECT) // can use this also, to eliminate MultipleBagFetchException
	List<Telephone> telephoneNumbers;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "student_project", joinColumns = {
			@JoinColumn(name = "student_id", referencedColumnName = "studentId") }, inverseJoinColumns = {
					@JoinColumn(name = "project_id", referencedColumnName = "projectId") })
//	@LazyCollection(LazyCollectionOption.FALSE) // still eagerly loads the collection, but eliminates the MultipleBagFetchException, i.e. we can have two or more eager loadings at once
	@Fetch(FetchMode.SELECT) // can use this also, to eliminate MultipleBagFetchException
	List<Project> projects;
}
