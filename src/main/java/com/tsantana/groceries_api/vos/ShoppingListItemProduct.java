package com.tsantana.groceries_api.vos;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "DTO for Shopping List item product")
public record ShoppingListItemProduct(

        @Schema(description = "Product ID", example = "123e4567-e89b-12d3-a456-426614174000")
        UUID id,

        @Schema(description = "Product name", example = "Leite Camponesa")
        String name
) {}
