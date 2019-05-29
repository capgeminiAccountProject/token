package com.capgemini.beans;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor
public class GetTokenRequest {
    private String email;
    private String pwd;

    @Override
    public String toString() {
        return "GetTokenRequest{" +
                "email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
