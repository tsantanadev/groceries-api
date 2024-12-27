package com.tsantana.groceries_api.services;

import com.tsantana.groceries_api.models.Store;
import com.tsantana.groceries_api.repositories.StoreRepository;
import com.tsantana.groceries_api.vos.StoreRequest;
import lombok.AllArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StoreService {

    private final StoreRepository repository;
    private final AddressService addressService;

    public Store create(final StoreRequest storeRequest) {
        final var address = addressService.create(getPoint(storeRequest.latitude(), storeRequest.longitude()));
        final var store = new Store(storeRequest.name(), address);
        return repository.save(store);
    }

    public List<Store> findByName(final String name) {
        return repository.findAllByName(name);
    }

    public List<Store> findByLocation(final Double latitude, final Double longitude) {
        Point point = getPoint(latitude, longitude);
        return repository.findAllByLocation(point, 5000.0);
    }

    private Point getPoint(final Double latitude, final Double longitude) {
        GeometryFactory factory = new GeometryFactory();
        Point point = factory.createPoint(new Coordinate(latitude, longitude));
        point.setSRID(4326);
        return point;
    }
}
