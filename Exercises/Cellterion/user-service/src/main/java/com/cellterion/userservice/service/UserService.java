package com.cellterion.userservice.service;

import java.util.List;

import com.cellterion.userservice.model.User;

public interface UserService {

	User saveUser(User user);

	User findUserById(Integer userId);

	List<User> findAllUsers();

}