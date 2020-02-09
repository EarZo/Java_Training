package com.example.studentservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentservice.model.Student;
import com.example.studentservice.service.StudentService;

@RestController
@RequestMapping("services")
public class StudentController {
	
	@Autowired
	StudentService studentService;

	@RequestMapping("hello")
	public String welcomeMessage() {
		return "Hello from Spring!";
	}
	
	@RequestMapping("students")
	public List<Student> getAllStudents(){
		return studentService.findAllStudents();
	}
	
	@RequestMapping("/student/{id}")
	public Student getStudent(@PathVariable int id) {
		return studentService.findStudentById(id);
	}
	
	@RequestMapping("/student")
	public Student saveStudent(@RequestBody Student student) {
		return studentService.saveStudent(student);
	}
}
