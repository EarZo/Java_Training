package com.example.studentservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
	
	public Allocation[] getStudentAllocations(Integer studentId){
		HttpHeaders httpHeaders = new HttpHeaders();
		
		HttpEntity<String> allocationsRequest = new HttpEntity<>("", httpHeaders);

		ResponseEntity<Allocation[]> allocationsResponse = restTemplate.exchange("http://localhost:8081/services/allocations/" + studentId, HttpMethod.GET, allocationsRequest, Allocation[].class);
		
		return allocationsResponse.getBody();
	}
}
