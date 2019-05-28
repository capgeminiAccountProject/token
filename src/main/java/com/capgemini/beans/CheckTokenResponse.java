package com.capgemini.beans;

import com.capgemini.exceptions.TokenException;

public class CheckTokenResponse {
    private String token;
    private String status;
    private TokenException tokenException;

    public CheckTokenResponse(String token, String status, TokenException tokenException) {
        this.token = token;
        this.status = status;
        this.tokenException = tokenException;
    }

    public CheckTokenResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TokenException getTokenException() {
        return tokenException;
    }

    public void setTokenException(TokenException tokenException) {
        this.tokenException = tokenException;
    }
}
