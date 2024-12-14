package com.tsantana.groceries_api.repositories;

import com.tsantana.groceries_api.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BrandRepository extends JpaRepository<Brand, UUID> {
    @Query("SELECT b FROM Brand b WHERE LOWER(b.name) = LOWER(:name)")
    List<Brand> findByName(@Param("name") final String name);

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN TRUE ELSE FALSE END FROM Brand b WHERE LOWER(b.name) = LOWER(:name)")
    boolean existsByName(@Param("name") final String name);
}
