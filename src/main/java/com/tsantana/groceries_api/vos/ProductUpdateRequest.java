package com.tsantana.groceries_api.vos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.util.UUID;

@Schema(description = "DTO for Product update")
public record ProductUpdateRequest(
    @NotBlank(message = "Product name is required")
    @Schema(description = "Product name", example = "Leite")
    String name,

    @Schema(description = "Group ID")
    UUID groupId,

    @Schema(description = "Brand ID")
    UUID brandId,

    @Schema(description = "Product price", example = "5.99")
    BigDecimal price) {}
