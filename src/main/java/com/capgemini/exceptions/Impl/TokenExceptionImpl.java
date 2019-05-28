package com.capgemini.exceptions.Impl;

import com.capgemini.exceptions.TokenException;

public class TokenExceptionImpl extends Exception implements TokenException{
    private int code;
    private String message;

    public TokenExceptionImpl(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "TokenException{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
