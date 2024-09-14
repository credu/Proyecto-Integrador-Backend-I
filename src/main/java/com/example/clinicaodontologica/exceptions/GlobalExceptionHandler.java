package com.example.clinicaodontologica.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mensaje: " + exception.getMessage());
    }

    @ExceptionHandler({ResourceBadRequestException.class})
    public ResponseEntity<String> handleResourceBadRequest(ResourceBadRequestException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mensaje: " + exception.getMessage());
    }
}
