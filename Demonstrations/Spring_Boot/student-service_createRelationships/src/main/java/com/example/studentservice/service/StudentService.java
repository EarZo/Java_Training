package com.example.studentservice.service;

import java.util.List;

import com.example.studentservice.model.Student;

public interface StudentService {

	Student saveStudent(Student student);
	
	Student findStudentById(int id);
	
	List<Student> findAllStudents();
}