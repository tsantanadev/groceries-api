package com.tsantana.groceries_api.services;

import com.tsantana.groceries_api.models.Product;
import com.tsantana.groceries_api.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public Product create(Product group) {
        return repository.save(group);
    }

    public Product update(Product group) {
        return repository.save(group);
    }

    public Product findByName(String name) {
        return repository.findByName(name);
    }

}
