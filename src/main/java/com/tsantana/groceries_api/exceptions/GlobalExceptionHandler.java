package com.tsantana.groceries_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        final ErrorMessage errorMessage = new ErrorMessage("Validation failed", errors);
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFountException.class)
    public ResponseEntity<ErrorMessage> handleNotFoundException(NotFountException ex) {
        return new ResponseEntity<>(new ErrorMessage(ex.getMessage(), null), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SchemaViolationException.class)
    public ResponseEntity<ErrorMessage> handleSchemaViolationException(SchemaViolationException ex) {
        return new ResponseEntity<>(new ErrorMessage(ex.getMessage(), null), HttpStatus.BAD_REQUEST);
    }
}
