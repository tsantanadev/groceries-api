package com.tsantana.groceries_api.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorMessage(
        String message,
        Map<String, String> errors
) {}
