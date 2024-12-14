package com.tsantana.groceries_api.exceptions;

public class NotFountException extends RuntimeException {
    public NotFountException(String message) {
        super(message);
    }
}
