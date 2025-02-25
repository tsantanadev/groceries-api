package com.tsantana.groceries_api.repositories;

import com.tsantana.groceries_api.models.Address;
import com.tsantana.groceries_api.models.ShoppingList;
import com.tsantana.groceries_api.models.ShoppingListItem;
import com.tsantana.groceries_api.vos.ShoppingListResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingList, UUID> { }