package com.tsantana.groceries_api.repositories;

import com.tsantana.groceries_api.models.ShoppingList;
import com.tsantana.groceries_api.models.ShoppingListItem;
import com.tsantana.groceries_api.vos.ShoppingListItemResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ShoppingListItemRepository extends JpaRepository<ShoppingListItem, UUID> {

    void deleteByShoppingListIdAndId(UUID shoppingListId, UUID id);

    @Query("""
                SELECT new com.tsantana.groceries_api.vos.ShoppingListItemResponse(
                    sli.id,
                    sli.quantity,
                    sli.unit,
                    sli.checked,
                    new com.tsantana.groceries_api.vos.ShoppingListItemProduct(
                        p.id,
                        p.name
                    ),
                    new com.tsantana.groceries_api.vos.ShoppingListItemProductGroup(
                        pg.id,
                        pg.name
                    )
                )
                FROM ShoppingListItem sli
                JOIN sli.shoppingList sl
                LEFT JOIN sli.product p
                LEFT JOIN sli.group pg
                WHERE sl.id = :id
            """)
    List<ShoppingListItemResponse> findAllByShoppingListId(final UUID id);
}
