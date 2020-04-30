package com.cellterion.userservice.service;

import com.cellterion.userservice.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    User findUserById(Integer userID);

    List<User> findAllUsers();

    User findByUsername(String username);

    Boolean checkExistenceByUsername(String username);

    Boolean checkExistenceByEmail(String email);
}
