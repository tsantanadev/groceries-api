package com.tsantana.groceries_api.vos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

@Schema(description = "DTO for Brand creation or update")
public record BrandRequest(

        @NotEmpty(message = "Brand name is required")
        @Schema(description = "Brand name", example = "Itambé")
        String name
) {
}
