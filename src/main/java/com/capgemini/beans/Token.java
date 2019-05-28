package com.capgemini.beans;

public class Token {

    private String email;
    private String token;

    public Token(String token) {
        this.token = token;
    }

    public Token() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
