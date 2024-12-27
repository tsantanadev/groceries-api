package com.tsantana.groceries_api.vos;

import com.tsantana.groceries_api.models.Store;

public record StoreResponse(
        String id,
        String name,
        AddressResponse address,
        String createdAt,
        String updatedAt
) {
    public StoreResponse(final Store store) {
        this(store.getId().toString(),
                store.getName(),
                new AddressResponse(store.getAddress()),
                store.getCreatedAt().toString(),
                store.getUpdatedAt().toString());
    }
}
