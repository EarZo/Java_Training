package com.cellterion.userservice.payload.response;

import lombok.Data;

public @Data class ResponseMessage {

    private String message;

    public ResponseMessage(String message) {
        this.message = message;
    }

}
