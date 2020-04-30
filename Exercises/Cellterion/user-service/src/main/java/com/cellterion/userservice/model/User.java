package com.cellterion.userservice.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "username"), @UniqueConstraint(columnNames = "email")})
public @Data class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer userId;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "userId")}, inverseJoinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "roleId")})
//	@LazyCollection(LazyCollectionOption.FALSE) // still eagerly loads the collection, but eliminates the MultipleBagFetchException, i.e. we can have two or more eager loadings at once
//	@Fetch(FetchMode.SELECT) // can use this also, to eliminate MultipleBagFetchException
	List<Role> roles;
}
