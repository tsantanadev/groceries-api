package com.tsantana.groceries_api.vos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

@Schema(description = "DTO for Shopping List creation or update")
public record ShoppingListRequest(

        @NotEmpty(message = "Shopping List name is required")
        @Schema(description = "Shopping List", example = "Casa")
        String name
) {
}
