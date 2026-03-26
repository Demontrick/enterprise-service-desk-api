package com.company.servicedesk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Map<String,Object>> handleValidation(
        MethodArgumentNotValidException ex){

    Map<String,Object> error = new HashMap<>();

    error.put("message","Validation failed");
    error.put("status",400);
    error.put("timestamp",LocalDateTime.now());

    return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
}

}