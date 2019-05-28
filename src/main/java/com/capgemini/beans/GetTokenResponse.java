package com.capgemini.beans;

import com.capgemini.exceptions.Impl.TokenExceptionImpl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @NoArgsConstructor @ToString

public class GetTokenResponse {
    private String token;
    private String status;
    private TokenExceptionImpl tokenException;

    public GetTokenResponse(String token, String status, TokenExceptionImpl tokenException) {
        this.token = token;
        this.status = status;
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
