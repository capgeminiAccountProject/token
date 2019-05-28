package com.capgemini.beans;

import com.capgemini.exceptions.TokenException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @NoArgsConstructor @ToString
public class CheckTokenResponse {
    private String token;
    private String status;
    private TokenException tokenException;

    public CheckTokenResponse(String token, String status, TokenException tokenException) {
        this.token = token;
        this.status = status;
        this.tokenException = tokenException;
    }
}
