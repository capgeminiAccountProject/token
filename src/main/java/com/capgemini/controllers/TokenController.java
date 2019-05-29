package com.capgemini.controllers;

import com.capgemini.beans.*;
import com.capgemini.constants.Constants;
import com.capgemini.exceptions.Impl.TokenExceptionImpl;
import com.capgemini.services.Impl.TokenServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TokenController {

    private static final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);

    @Autowired
    private TokenServiceImpl tokenServiceImpl;

    @CrossOrigin(origins = "*")
    @PostMapping("/gettoken")
    public ResponseEntity<GetTokenResponse> getToken(@RequestBody GetTokenRequest getTokenRequest) {

        logger.info("gettoken request : " + getTokenRequest.toString());


        Token token = tokenServiceImpl.getNewToken(getTokenRequest);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("authorization", token.getToken());

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(new GetTokenResponse(token.getToken(), Constants.SUCCESS, null));
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/checktokenapp")
    public ResponseEntity<CheckTokenResponse> checkTokenApp(@RequestHeader(value="authorization") String token,
                                                       @RequestBody CheckTokenRequest checkTokenRequest) {
        logger.info("checkTokenApp request: " + checkTokenRequest.toString());

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(Constants.AUTHORIZATION, Constants.TOKEN_IS_NOT_CORRECT);

        if (!token.equalsIgnoreCase(checkTokenRequest.getToken())) return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(new CheckTokenResponse(null, Constants.FAIL,
                        new TokenExceptionImpl(1, "token in the header and in the body is not much")));

        responseHeaders = new HttpHeaders();
        responseHeaders.set(Constants.AUTHORIZATION, token);

        if (tokenServiceImpl.checkTokenApp(token).equalsIgnoreCase(Constants.SUCCESS)) return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(new CheckTokenResponse(token, Constants.SUCCESS, null));

        responseHeaders = new HttpHeaders();
        responseHeaders.set(Constants.AUTHORIZATION, Constants.TOKEN_IS_NOT_CORRECT);

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(new CheckTokenResponse(Constants.TOKEN_IS_NOT_CORRECT, Constants.FAIL, new TokenExceptionImpl(1, Constants.TOKEN_IS_NOT_CORRECT)));
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/checktokenuser")
    public ResponseEntity<CheckTokenResponse> checkTokenUser(@RequestHeader(value="authorization") String token,
                                                            @RequestBody CheckTokenRequest checkTokenRequest) {
        logger.info("checkTokenUser request: " + checkTokenRequest.toString());


        HttpHeaders responseHeaders = new HttpHeaders();
        getHeader(responseHeaders, Constants.FAIL, null);

        if (!token.equalsIgnoreCase(checkTokenRequest.getToken())) return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(new CheckTokenResponse(null, Constants.FAIL, new TokenExceptionImpl(1, "token in the header and in the body is not much")));

        responseHeaders = new HttpHeaders();
        getHeader(responseHeaders, Constants.SUCCESS, token);

        if (tokenServiceImpl.checkTokenUser(token, checkTokenRequest.getEmail()).equalsIgnoreCase(Constants.SUCCESS)) return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(new CheckTokenResponse(token, Constants.SUCCESS, null));

        responseHeaders = new HttpHeaders();
        getHeader(responseHeaders, Constants.FAIL, "");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(new CheckTokenResponse(Constants.TOKEN_IS_NOT_CORRECT, Constants.FAIL, new TokenExceptionImpl(1, Constants.TOKEN_IS_NOT_CORRECT)));
    }

    private void getHeader(HttpHeaders responseHeaders, String state, String token) {
        if (state.equalsIgnoreCase(Constants.FAIL)) {
            responseHeaders.set(Constants.AUTHORIZATION, Constants.TOKEN_IS_NOT_CORRECT);
        } else {
            responseHeaders.set(Constants.AUTHORIZATION, token);
        }
    }

}
