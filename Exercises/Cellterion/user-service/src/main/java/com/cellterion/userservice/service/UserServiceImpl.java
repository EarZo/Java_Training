package com.cellterion.userservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cellterion.userservice.model.User;
import com.cellterion.userservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User findUserById(Integer userId) {
		Optional<User> userOptional = userRepository.findById(userId);
		
		if(userOptional.isPresent())
			return userOptional.get();
		return null;
	}

	@Override
	public List<User> findAllUsers(){
		return userRepository.findAll();
	}
	
}
