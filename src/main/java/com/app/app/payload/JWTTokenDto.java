package com.app.app.payload;

import com.app.app.service.JWTService;

public class JWTTokenDto {
    private String token;
    private String tokenType;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
