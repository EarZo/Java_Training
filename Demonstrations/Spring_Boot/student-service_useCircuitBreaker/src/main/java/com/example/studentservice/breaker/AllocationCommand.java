package com.example.studentservice.breaker;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.studentservice.model.Student;
import com.example.studentservice.sharedModel.Allocation;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class AllocationCommand extends HystrixCommand<Allocation[]> {

	Student student;
	HttpHeaders httpHeaders;
	RestTemplate restTemplate;
	
	public AllocationCommand(Student student, HttpHeaders httpHeaders, RestTemplate restTemplate) {
		super(HystrixCommandGroupKey.Factory.asKey("default"));
		this.student = student;
		this.httpHeaders = httpHeaders;
		this.restTemplate = restTemplate;
	}

	@Override
	protected Allocation[] run() throws Exception {
		HttpEntity<String> allocationsRequest = new HttpEntity<>("", httpHeaders);

		ResponseEntity<Allocation[]> allocationsResponse = restTemplate.exchange("http://allocationService/services/allocations/" + student.getStudentId(), HttpMethod.GET, allocationsRequest, Allocation[].class);
		
		return allocationsResponse.getBody();
	}

	@Override
	protected Allocation[] getFallback() {
		return new Allocation[1];
	}
	
}
