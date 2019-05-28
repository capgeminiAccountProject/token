package com.capgemini.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @NoArgsConstructor @ToString
public class CheckTokenRequest {
    private String token;
    private String email;

    public CheckTokenRequest(String token, String email) {
        this.token = token;
        this.email = email;
    }
}
