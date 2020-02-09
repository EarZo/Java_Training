package com.example.studentservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentservice.model.Project;
import com.example.studentservice.model.Student;
import com.example.studentservice.model.Telephone;
import com.example.studentservice.service.StudentService;

@RestController
@RequestMapping("/services")
public class StudentController {

	@Autowired
	StudentService studentService;

	@RequestMapping("/hello")
	public String welcomeMessage() {
		return "Hello from Spring!";
	}

	@RequestMapping("/students")
	public List<Student> getAllStudents() {
		return studentService.findAllStudents();
	}

	@RequestMapping("/student/{id}")
	public Student getStudent(@PathVariable int id) {
		return studentService.findStudentById(id);
	}

	@RequestMapping("/student")
	public Student saveStudent(@RequestBody Student student) {
		if (student.getTelephoneNumbers() != null)
			for (Telephone telephone : student.getTelephoneNumbers())
				telephone.setStudent(student);
		if (student.getProjects() != null) {
			for (Project project : student.getProjects()) {
				if (project.getStudents() != null)
					project.getStudents().add(student);
			}
		}
		
		return studentService.saveStudent(student);
	}
}
