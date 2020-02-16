package com.example.studentservice.service;

import java.util.List;

import com.example.studentservice.model.Student;
import com.example.studentservice.sharedModel.Allocation;

public interface StudentService {

	Student saveStudent(Student student);
	
	Student findStudentById(Integer id);
	
	List<Student> findAllStudents();
	
	Allocation[] getStudentAllocations(Student student);
}