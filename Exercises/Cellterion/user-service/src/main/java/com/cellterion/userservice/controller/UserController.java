package com.cellterion.userservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.cellterion.userservice.model.Role;
import com.cellterion.userservice.model.RoleType;
import com.cellterion.userservice.payload.request.SignInRequest;
import com.cellterion.userservice.payload.request.SignUpRequest;
import com.cellterion.userservice.payload.response.JwtResponse;
import com.cellterion.userservice.payload.response.ResponseMessage;
import com.cellterion.userservice.security.jwt.JwtUtils;
import com.cellterion.userservice.service.RoleService;
import com.cellterion.userservice.service.UserDetailsImpl;
import com.cellterion.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.cellterion.userservice.model.User;

import javax.validation.Valid;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/services")
public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@GetMapping("/hello")
	public String sayHello() {
		return "Hello from User-Service!";
	}

	@PostMapping("/role")
	public ResponseEntity<?> saveRole(@RequestBody Role role){
		Role savedRole = roleService.saveRole(role);

		if (savedRole == null) {
			return ResponseEntity.unprocessableEntity().body(new ResponseMessage("Sorry, cannot save the provided role!"));
		} else {
			return ResponseEntity.ok().body(new ResponseMessage("User-Role Saved Successfully!"));
		}
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignInRequest signInRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		//create sign-in response entity
		JwtResponse jwtResponse = new JwtResponse();
		jwtResponse.setAccessToken(jwt);
		jwtResponse.setId(userDetails.getId());
		jwtResponse.setUsername(userDetails.getUsername());
		jwtResponse.setEmail(userDetails.getEmail());
		jwtResponse.setRoles(roles);

		return ResponseEntity.ok(jwtResponse);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		if (userService.checkExistenceByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new ResponseMessage("Error: Username is already taken!"));
		}

		if (userService.checkExistenceByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new ResponseMessage("Error: Email is already in use!"));
		}

		//create new user
		User user = new User();
		user.setUsername(signUpRequest.getUsername());
		user.setEmail(signUpRequest.getEmail());
		user.setPassword(encoder.encode(signUpRequest.getPassword()));

		List<String> strRoles = signUpRequest.getRole();
		List<Role> roles = new ArrayList<>();

		if (strRoles == null) {
			Role userRole = roleService.findByRoleType(RoleType.ROLE_USER);
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				if ("admin".equals(role)) {
					Role adminRole = roleService.findByRoleType(RoleType.ROLE_ADMIN);
					roles.add(adminRole);
				} else {
					Role userRole = roleService.findByRoleType(RoleType.ROLE_USER);
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userService.saveUser(user);

		return ResponseEntity.ok(new ResponseMessage("User Registered Successfully!"));
	}
	
}
