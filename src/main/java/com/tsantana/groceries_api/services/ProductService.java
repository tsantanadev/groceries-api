package com.tsantana.groceries_api.services;

import com.tsantana.groceries_api.exceptions.NotFoundException;
import com.tsantana.groceries_api.exceptions.SchemaViolationException;
import com.tsantana.groceries_api.models.Product;
import com.tsantana.groceries_api.repositories.ProductRepository;
import com.tsantana.groceries_api.vos.ProductRequest;
import com.tsantana.groceries_api.vos.ProductUpdateRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final BrandService brandService;
    private final GroupService groupService;

    public Product create(final ProductRequest productRequest) {
        if (repository.existsByName(productRequest.name())) {
            throw new SchemaViolationException(String.format("Product with name %s already exists", productRequest.name()));
        }

        if (repository.existsByGtin(productRequest.gtin())) {
            throw new SchemaViolationException(String.format("Product with gtin %s already exists", productRequest.gtin()));
        }

        validateBrand(productRequest.brandId());
        validateGroup(productRequest.groupId());

        final var product = new Product(productRequest.name(), productRequest.gtin(), productRequest.groupId(), productRequest.brandId(), productRequest.price());

        return repository.save(product);
    }

    public Product update(final ProductUpdateRequest productRequest, final UUID id) {
        final var product = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Product not found")
        );

        validateBrand(productRequest.brandId());
        validateGroup(productRequest.groupId());

        product.setName(productRequest.name());
        product.setBrandId(productRequest.brandId());
        product.setGroupId(productRequest.groupId());
        product.setBestPrice(productRequest.price());
        product.setUpdatedAt(LocalDateTime.now());

        return repository.save(product);
    }

    public Product findByName(String name) {
        return repository.findByName(name).orElseThrow(
                () -> new NotFoundException("Product not found")
        );
    }

    private void validateGroup(final UUID groupId) {
        if (groupId != null) {
            groupService.existsById(groupId);
        }
    }

    private void validateBrand(final UUID brandId) {
        if (brandId != null) {
            final var brandExists = brandService.existsById(brandId);
            if (!brandExists) {
                throw new NotFoundException(String.format("Brand with id %s does not exist", brandId));
            }
        }
    }

    public void existsById(final UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Product not found");
        }
    }
}
