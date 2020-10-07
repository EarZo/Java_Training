package com.cellterion.userservice.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cellterion.userservice.model.WebsiteUser;
import com.cellterion.userservice.repository.UserRepository;
import com.cellterion.userservice.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.cellterion.userservice.security.SecurityConstants.SECRET;
import static com.cellterion.userservice.security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/services")
//@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/hello")
    public String sayHello(@RequestHeader(value="Authorization") String authorizationToken) {

        String websiteUserUsernameAsString = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                .build()
                .verify(authorizationToken.replace(TOKEN_PREFIX, ""))
                .getSubject();

        WebsiteUser websiteUser = userRepository.findByUsername(websiteUserUsernameAsString).get();

        return "Hello from User-Service! Welcome " + websiteUser.getFirstName() + " " + websiteUser.getLastName() + "!";
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody WebsiteUser websiteUser) {
        userDetailsService.saveWebsiteUser(websiteUser);
    }
}
