package com.tsantana.groceries_api.vos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

@Schema(description = "DTO for Group creation or update")
public record GroupRequest(

        @NotEmpty(message = "Group name is required")
        @Schema(description = "Group name", example = "Leite")
        String name
) {
}
