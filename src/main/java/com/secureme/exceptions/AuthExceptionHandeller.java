package com.secureme.exceptions;

import com.secureme.exception.ApiError;
import com.secureme.exception.GlobalExceptionHandeller;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

public class AuthExceptionHandeller extends GlobalExceptionHandeller {

    /*@ExceptionHandler(UserAuthenticationException.class)
    public ResponseEntity<ApiError> handleUserDisabledException(UserAuthenticationException ex, HttpServletRequest request){
        ApiError exceptionResponse = buildAPIError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
                "500", ex.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }*/

}
