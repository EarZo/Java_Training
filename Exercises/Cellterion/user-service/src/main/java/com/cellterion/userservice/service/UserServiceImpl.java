package com.cellterion.userservice.service;

import com.cellterion.userservice.model.User;
import com.cellterion.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User saveUser(User user){
        return userRepository.save(user);
    }

    @Override
    public User findUserById(Integer userID){
        Optional<User> userOptional = userRepository.findById(userID);

        if(userOptional.isPresent())
            return userOptional.get();
        return null;
    }

    @Override
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username){

        Optional<User> userOptional = userRepository.findByUsername(username);

        if(userOptional.isPresent())
            return userOptional.get();
        return null;
    }

    @Override
    public Boolean checkExistenceByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean checkExistenceByEmail(String email){
        return userRepository.existsByEmail(email);
    }
}
