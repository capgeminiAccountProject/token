package com.capgemini.beans;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @NoArgsConstructor @ToString
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
