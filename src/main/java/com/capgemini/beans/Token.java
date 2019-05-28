package com.capgemini.beans;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @NoArgsConstructor @ToString
public class Token {

    private String email;
    private String token;

    public Token(String token) {
        this.token = token;
    }
}
