package com.example.studentservice.service;

import java.util.List;
import java.util.Optional;

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
		Optional<Student> s = studentRepository.findById(id);
		
		if(s.isPresent())
			return s.get();
		return null;
	}

	@Override
	public List<Student> findAllStudents() {
		return studentRepository.findAll();
	}
}
