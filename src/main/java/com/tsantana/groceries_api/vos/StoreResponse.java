package com.tsantana.groceries_api.vos;

import com.tsantana.groceries_api.models.Store;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(name = "StoreResponse")
public record StoreResponse(
        @Schema(description = "Store's identifier", example = "123e4567-e89b-12d3-a456-426614174000")
        UUID id,
        @Schema(description = "Store's name", example = "Supermarket")
        String name,
        @Schema(description = "Store's address")
        AddressResponse address,
        @Schema(description = "Distance in meters from the store to the user's location", example = "5000.0")
        Double distance,
        @Schema(description = "Store's creation date and time", example = "2021-12-31T23:59:59")
        LocalDateTime createdAt,
        @Schema(description = "Store's last update date and time", example = "2021-12-31T23:59:59")
        LocalDateTime updatedAt
) {
    public StoreResponse(final Store store) {
        this(store.getId(),
                store.getName(),
                new AddressResponse(store.getAddress()),
                null,
                store.getCreatedAt(),
                store.getUpdatedAt());
    }

    public StoreResponse(
            final UUID id,
            final String name,
            final AddressResponse address,
            final Object distance,
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt) {
        this(
                id,
                name,
                address,
                (Double) distance,
                createdAt,
                updatedAt
        );
    }
}
