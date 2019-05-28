package com.capgemini.exceptions;

public interface TokenException {
    int getCode();
    void setCode(int code);
    String getMessage();
    void setMessage(String message);
}
