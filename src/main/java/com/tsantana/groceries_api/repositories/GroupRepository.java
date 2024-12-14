package com.tsantana.groceries_api.repositories;

import com.tsantana.groceries_api.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GroupRepository extends JpaRepository<Group, UUID> {
    Group findByName(String name);
}
