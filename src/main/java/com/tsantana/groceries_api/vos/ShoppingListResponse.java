package com.tsantana.groceries_api.vos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "DTO for Shopping List response")
public record ShoppingListResponse(
        @Schema(description = "Shopping List ID", example = "123e4567-e89b-12d3-a456-426614174000")
        UUID id,
        @Schema(description = "Shopping List name", example = "Supermarket")
        String name
) { }
