package com.mevlut.onlineshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
    This class controls our custom exceptions for an entity
 */

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> customException(CustomException exception) {
        return new ResponseEntity<String>(exception.getErrorMessage(), HttpStatus.NOT_FOUND);
    }

}