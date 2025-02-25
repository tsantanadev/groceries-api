package com.tsantana.groceries_api.vos;

import com.tsantana.groceries_api.models.UnitEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

@Schema(description = "DTO for Shopping List Item creation")
public record ShoppingListItemRequest(
    @Schema(description = "Product ID", example = "123e4567-e89b-12d3-a456-426614174000")
    UUID productId,

    @Schema(description = "Group ID", example = "123e4567-e89b-12d3-a456-426614174000")
    UUID groupId,

    @NotNull
    @Schema(description = "Quantity", example = "1.0")
    BigDecimal quantity,

    @NotNull
    @Schema(description = "Unit", example = "UN")
    UnitEnum unit
) {}
