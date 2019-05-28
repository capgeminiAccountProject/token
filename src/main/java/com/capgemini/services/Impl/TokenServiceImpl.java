package com.capgemini.services.Impl;

import com.capgemini.beans.GetTokenRequest;
import com.capgemini.beans.Token;
import com.capgemini.constants.Constants;
import com.capgemini.services.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TokenServiceImpl implements TokenService {

    public Token getNewToken(GetTokenRequest getTokenRequest) {
        long time = getTokenRequest.getEmail().startsWith("a") ? 60000 : 30 * 60 * 1000;
        String token = createJWT(Constants.TOKEN, Constants.PWD, getTokenRequest.getEmail(), time );
    return new Token(token);
    }

    public String checkTokenApp (String token) {
        String retVal = Constants.SUCCESS;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        try {
            return  (decodeJWT(token).getExpiration().compareTo(now) > 0
                    && decodeJWT(token).getId().equalsIgnoreCase(Constants.TOKEN)) ? retVal : Constants.FAIL;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Constants.FAIL;
    }

    public String checkTokenUser (String token, String email) {
        String retVal = Constants.SUCCESS;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        try {
            return  (decodeJWT(token).getExpiration().compareTo(now) > 0
                    && decodeJWT(token).getSubject().equalsIgnoreCase(email))
                    ? retVal : Constants.FAIL;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Constants.FAIL;
    }

    public String createJWT(String id, String issuer, String subject, long ttlMillis) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //LocalDateTime now = LocalDateTime.now();

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(Constants.SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis > 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public Claims decodeJWT(String jwt) {

        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(Constants.SECRET_KEY))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }
}
