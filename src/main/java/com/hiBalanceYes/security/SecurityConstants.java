package com.hiBalanceYes.security;

public class SecurityConstants {
    public static final String SECRET = "secret";
    public static final long EXPIRATION_TIME = 1 * 10 * 60; // 5 min


    public static final String[] NO_AUTH_URL = {
            "/",
            "/home",
            "/login",
            "/users/register",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**"
    };
}
