package com.cellterion.userservice.controller;

import com.cellterion.userservice.model.WebsiteUser;
import com.cellterion.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/services")
//@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from WebsiteUser-Service!";
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody WebsiteUser websiteUser) {
        websiteUser.setPassword(bCryptPasswordEncoder.encode(websiteUser.getPassword()));
        userRepository.save(websiteUser);
    }
}
