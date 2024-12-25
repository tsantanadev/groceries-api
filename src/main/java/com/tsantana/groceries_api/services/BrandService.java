package com.tsantana.groceries_api.services;

import com.tsantana.groceries_api.exceptions.NotFoundException;
import com.tsantana.groceries_api.exceptions.SchemaViolationException;
import com.tsantana.groceries_api.models.Brand;
import com.tsantana.groceries_api.repositories.BrandRepository;
import com.tsantana.groceries_api.vos.BrandRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BrandService {

    private final BrandRepository repository;

    public Brand create(final BrandRequest brand) {
        final var exists = repository.existsByName(brand.name());
        if (exists) {
            throw new SchemaViolationException(String.format("Brand with name %s already exists", brand.name()));
        }
        final Brand newBrand = new Brand(brand.name());
        return repository.save(newBrand);
    }

    public Brand update(final BrandRequest brand, final UUID id) {
        final Brand existingBrand = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Brand not found")
        );

        existingBrand.setName(brand.name());
        existingBrand.setUpdatedAt(LocalDateTime.now());
        return repository.save(existingBrand);
    }

    public List<Brand> findByName(final String name) {
        return repository.findByName(name);
    }

    public Boolean existsById(final UUID id) {
        return repository.existsById(id);
    }
}
