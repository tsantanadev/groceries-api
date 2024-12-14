package com.tsantana.groceries_api.exceptions;

public class SchemaViolationException extends RuntimeException {
    public SchemaViolationException(String message) {
        super(message);
    }
}
