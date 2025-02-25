package com.tsantana.groceries_api.vos;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "DTO for Shopping List item product group")
public record ShoppingListItemProductGroup(

        @Schema(description = "Group ID", example = "123e4567-e89b-12d3-a456-426614174000")
        UUID id,

        @Schema(description = "Group name", example = "Leite")
        String name
) {}
