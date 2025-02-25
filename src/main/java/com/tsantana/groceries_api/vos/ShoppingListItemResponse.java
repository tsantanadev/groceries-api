package com.tsantana.groceries_api.vos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tsantana.groceries_api.models.UnitEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "DTO for Shopping List item response")
public record ShoppingListItemResponse(
        @Schema(description = "Item list id", example = "1")
        UUID id,

        @Schema(description = "Quantity", example = "2.0")
        BigDecimal quantity,

        @Schema(description = "Unit", example = "LT")
        UnitEnum unit,

        @Schema(description = "Checked", example = "false")
        Boolean checked,

        @Schema(description = "Product")
        ShoppingListItemProduct product,

        @Schema(description = "Product Group")
        ShoppingListItemProductGroup group

) {
}
