package com.example.studentservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.studentservice.model.Student;
import com.example.studentservice.model.Telephone;
import com.example.studentservice.service.StudentService;
import com.example.studentservice.sharedModel.Allocation;

@RestController
@RequestMapping("/services")
public class StudentController {

	@Autowired
	StudentService studentService;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	public RestTemplate getAllocationBean() {
		return new RestTemplate();
	}

	@GetMapping("/hello")
	public String welcomeMessage() {
		return "Hello from Spring!";
	}

	@GetMapping("/students")
	public List<Student> getAllStudents() {
		return studentService.findAllStudents();
	}

	@GetMapping("/student/{id}")
	public Student getStudent(@PathVariable int id) {
		
//		HttpHeaders headers = new HttpHeaders();
//		
//		HttpEntity<Allocation[]> allocationsRequest = new HttpEntity<Allocation[]>(new Allocation());
//
//		ResponseEntity<Allocation[]> allocationsResponse = restTemplate.exchange("http://localhost:8081/services/allocation" + id, HttpMethod.GET, allocationsRequest, Allocation.class);
//		
//		List<Allocation> studentAllocations = allocationsResponse.getBody().;
//		
//		Student student = studentService.findStudentById(id);
//		
//		student.setStudentAllocations(studentAllocations);
		
		return student;
	}

	@PostMapping("/student")
	public Student saveStudent(@RequestBody Student student) {
		
		if (student.getTelephoneNumbers() != null)
			for (Telephone telephone : student.getTelephoneNumbers())
				telephone.setStudent(student);
		
		return studentService.saveStudent(student);
	}
}
