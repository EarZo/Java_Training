package com.example.studentservice.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentservice.model.Student;
import com.example.studentservice.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public Student findStudentById(int id) {
		return studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Sorry! Requested student not found! Please check the Id and try again."));
	}

	@Override
	public List<Student> findAllStudents() {
		return studentRepository.findAll();
	}
}
