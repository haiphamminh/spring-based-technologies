package com.example.controller;

import com.example.exception.NotFoundException;
import com.example.transform.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerAdviser {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNFE(NotFoundException nfe) {
        return ErrorResponse.builder()
                            .errorCode(NotFoundException.ERROR_CODE)
                            .message(nfe.getMessage())
                            .build();
    }
}
