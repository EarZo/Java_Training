package com.cellterion.userservice.model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Entity
public @Data class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer roleId;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	RoleType roleType;

	public Role() {
	}

	public Role(RoleType roleType) {
		this.roleType = roleType;
	}
	
	@ManyToMany(mappedBy = "roles")
	@JsonIgnore
	List<User> users;
}
