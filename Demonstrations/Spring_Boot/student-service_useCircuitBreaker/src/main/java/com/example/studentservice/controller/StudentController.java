package com.example.studentservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentservice.model.Student;
import com.example.studentservice.model.Telephone;
import com.example.studentservice.service.StudentService;

@RestController
@RequestMapping("/services")
public class StudentController {

	@Autowired
	StudentService studentService;

	@GetMapping("/hello")
	public String welcomeMessage() {
		return "Hello from Student-Service!";
	}

	@PostMapping("/student")
	public Student saveStudent(@RequestBody Student student) {
		
		if (student.getTelephoneNumbers() != null)
			for (Telephone telephone : student.getTelephoneNumbers())
				telephone.setStudent(student);
		
		return studentService.saveStudent(student);
	}

	@GetMapping("/student/{studentId}")
	public Student getStudent(@PathVariable Integer studentId) {
		Student student = studentService.findStudentById(studentId);
		student.setStudentAllocations(studentService.getStudentAllocations(student));
		
		return student;
	}

	@GetMapping("/students")
	public List<Student> getAllStudents() {
		List<Student> studentList = studentService.findAllStudents();
		
		for(Student student : studentList) {
			student.setStudentAllocations(studentService.getStudentAllocations(student));
		}
		
		return studentList;
	}
}
