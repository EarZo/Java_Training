package com.cellterion.userservice.payload.response;

import lombok.Data;

import java.util.List;

public @Data class JwtResponse {

    private String accessToken;
    private String type = "Bearer";
    private Integer id;
    private String username;
    private String email;
    private List<String> roles;

}
