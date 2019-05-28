package com.capgemini.beans;

public class CheckTokenRequest {
    private String token;
    private String email;

    public CheckTokenRequest(String token, String email) {
        this.token = token;
        this.email = email;
    }

    public CheckTokenRequest() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
