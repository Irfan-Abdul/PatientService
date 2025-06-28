package com.patient_management.patient_service.exceptions;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> error = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> error.put(fieldError.getField(), fieldError.getDefaultMessage()));
        log.warn("Input data validation failed {}",error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex){
        Map<String,String> errors = new HashMap<>();
        errors.put("error message", ex.getMessage());
        log.warn("Email already exists {}",ex.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleUserNotFoundException(UserNotFoundException ex){
        log.warn(ex.getMessage());

        Map<String,String> errors= new HashMap<>();
        errors.put("error message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }
}
