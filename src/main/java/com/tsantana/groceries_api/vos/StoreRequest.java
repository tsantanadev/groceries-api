package com.tsantana.groceries_api.vos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.geo.Point;

@Schema(description = "DTO for Store creation")
public record StoreRequest(
        @NotBlank
        String name,

        @NotNull
        Double latitude,

        @NotNull
        Double longitude
) { }
