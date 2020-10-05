package com.cellterion.userservice.security;

public class SecurityConstants {

    public static final String SECRET = "#cElltEr10n#";
    public static final long EXPIRATION_TIME = 3600000; // 1 hour
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/services/sign-up";

}
