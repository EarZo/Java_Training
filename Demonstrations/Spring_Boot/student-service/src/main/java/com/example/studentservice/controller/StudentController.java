package com.example.studentservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentservice.model.Student;

@RestController
@RequestMapping("services")
public class StudentController {

	@RequestMapping("hello")
	public String welcomeMessage() {
		
		return "Hello from Spring!";
	}
	
	@RequestMapping("students")
	public List<Student> getAllStudents(){
		return Student.getAllStudents();
	}
}
