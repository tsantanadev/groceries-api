package com.tsantana.groceries_api.vos;

import com.tsantana.groceries_api.models.Address;

public record AddressResponse(
        String id,
        String street,
        String number,
        String complement,
        String neighborhood,
        String city,
        String state,
        String country,
        Double latitude,
        Double longitude
) {
    public AddressResponse(final Address address) {
        this(address.getId().toString(),
                address.getStreet(),
                address.getNumber(),
                address.getComplement(),
                address.getNeighborhood(),
                address.getCity(),
                address.getState(),
                address.getCountry(),
                address.getLocation().getY(),
                address.getLocation().getX());
    }
}
