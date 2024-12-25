package com.tsantana.groceries_api.repositories;

import com.tsantana.groceries_api.models.Product;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) = LOWER(:name)")
    Optional<Product> findByName(final String name);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END FROM Product p WHERE LOWER(p.name) = LOWER(:name)")
    boolean existsByName(final String name);

    @Query("SELECT p FROM Product p WHERE p.gtin = :gtin")
    Optional<Product> findByGtin(final String gtin);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END FROM Product p WHERE p.gtin = :gtin")
    boolean existsByGtin(final String gtin);
}
