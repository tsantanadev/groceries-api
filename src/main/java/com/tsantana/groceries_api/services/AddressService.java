package com.tsantana.groceries_api.services;

import com.tsantana.groceries_api.models.Address;
import com.tsantana.groceries_api.repositories.AddressRepository;
import lombok.AllArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepository repository;
    private final GeolocationService geolocationService;

    public Address create(final Point point) {
        // TODO: Implement the method to create an address from a point
        return repository.save(new Address(point));
    }
}
