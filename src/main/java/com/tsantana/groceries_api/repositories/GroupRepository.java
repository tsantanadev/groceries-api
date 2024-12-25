package com.tsantana.groceries_api.repositories;

import com.tsantana.groceries_api.models.Group;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GroupRepository extends JpaRepository<Group, UUID> {

    @Query("SELECT g FROM Group g WHERE LOWER(g.name) = LOWER(:name)")
    Optional<Group> findByName(String name);

    @Query("SELECT CASE WHEN COUNT(g) > 0 THEN TRUE ELSE FALSE END FROM Group g WHERE LOWER(g.name) = LOWER(:name)")
    Boolean existsByName(String name);
}
