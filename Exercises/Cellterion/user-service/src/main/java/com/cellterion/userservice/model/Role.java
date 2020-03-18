package com.cellterion.userservice.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Entity
public @Data class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer roleId;
	
	String roleName;
	
	@ManyToMany(mappedBy = "roles")
	@JsonIgnore
	List<User> users;
}
