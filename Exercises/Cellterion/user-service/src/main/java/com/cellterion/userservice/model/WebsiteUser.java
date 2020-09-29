package com.cellterion.userservice.model;

import lombok.Data;

import javax.persistence.*;

@Entity
public @Data class WebsiteUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer userId;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
}
