package com.cellterion.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cellterion.userservice.model.User;
import com.cellterion.userservice.service.UserService;

@RestController
@RequestMapping("/services")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/hello")
	public String sayHello() {
		return "Hello from User-Service!";
	}
	
	@PostMapping("/user")
	public User saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.findAllUsers();
	}

	@GetMapping("/user/{id}")
	public User getuser(@PathVariable Integer id) {
		return userService.findUserById(id);
	}
	
}
