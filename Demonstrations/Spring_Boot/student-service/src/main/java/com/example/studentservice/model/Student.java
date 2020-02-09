package com.example.studentservice.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@XmlRootElement
public class Student {

	String name;
	String city;

	public Student() {
	}

	public Student(String name, String city) {
		this.name = name;
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public static List<Student> getAllStudents(){
		
		List<Student> students = new ArrayList<>();
		students.add(new Student("Saman", "Galle"));
		students.add(new Student("Kamal", "Colombo"));
		students.add(new Student("Nimal", "Jaffna"));
		students.add(new Student("Amal", "Ampara"));
		
		return students;
	}
}
