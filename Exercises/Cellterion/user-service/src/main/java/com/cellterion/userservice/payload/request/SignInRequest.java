package com.cellterion.userservice.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

public @Data class SignInRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
