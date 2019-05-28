package com.capgemini.beans;

import com.capgemini.exceptions.Impl.TokenExceptionImpl;

public class GetTokenResponse {
    private String token;
    private String status;
    private TokenExceptionImpl tokenException;

    public GetTokenResponse() {
    }

    public GetTokenResponse(String token, String status, TokenExceptionImpl tokenException) {
        this.token = token;
        this.status = status;
        this.tokenException = tokenException;
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

    public TokenExceptionImpl getTokenException() {
        return tokenException;
    }

    public void setTokenException(TokenExceptionImpl tokenException) {
        this.tokenException = tokenException;
    }

    @Override
    public String toString() {
        return "GetTokenResponse{" +
                "token='" + token + '\'' +
                ", status='" + status + '\'' +
                ", tokenException=" + tokenException +
                '}';
    }
}
