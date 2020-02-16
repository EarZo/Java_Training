package com.example.studentservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.studentservice.breaker.AllocationCommand;
import com.example.studentservice.model.Student;
import com.example.studentservice.repository.StudentRepository;
import com.example.studentservice.sharedModel.Allocation;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public Student findStudentById(Integer id) {
		Optional<Student> s = studentRepository.findById(id);
		
		if(s.isPresent())
			return s.get();
		return null;
	}

	@Override
	public List<Student> findAllStudents() {
		return studentRepository.findAll();
	}
	
	public Allocation[] getStudentAllocations(Student student){
		HttpHeaders httpHeaders = new HttpHeaders();
		
		AllocationCommand allocationCommand = new AllocationCommand(student, httpHeaders, restTemplate);
		
		return allocationCommand.execute();
	}
}
