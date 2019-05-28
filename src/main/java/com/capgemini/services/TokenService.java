package com.capgemini.services;

import com.capgemini.beans.GetTokenRequest;
import com.capgemini.beans.Token;
import io.jsonwebtoken.Claims;

public interface TokenService {
    Token getNewToken(GetTokenRequest getTokenRequest);
    String checkTokenApp (String token);
    String checkTokenUser (String token, String email);
    String createJWT(String id, String issuer, String subject, long ttlMillis);
    Claims decodeJWT(String jwt);
}
