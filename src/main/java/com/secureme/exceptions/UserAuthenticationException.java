package com.secureme.exceptions;

public class UserAuthenticationException extends RuntimeException{

    public UserAuthenticationException(String message){
        super(message);
    }
}
