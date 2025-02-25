package com.tsantana.groceries_api.services;

import com.tsantana.groceries_api.exceptions.NotFoundException;
import com.tsantana.groceries_api.exceptions.SchemaViolationException;
import com.tsantana.groceries_api.models.ShoppingList;
import com.tsantana.groceries_api.models.ShoppingListItem;
import com.tsantana.groceries_api.repositories.ShoppingListItemRepository;
import com.tsantana.groceries_api.repositories.ShoppingListRepository;
import com.tsantana.groceries_api.vos.ShoppingListItemRequest;
import com.tsantana.groceries_api.vos.ShoppingListItemResponse;
import com.tsantana.groceries_api.vos.ShoppingListResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
    private final ShoppingListItemRepository shoppingListItemRepository;
    private final ProductService productService;
    private final GroupService groupService;

    public void addItem(final UUID id, final ShoppingListItemRequest itemRequest) {
        final var shoppingList = shoppingListRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Shopping list not found")
        );

        validateListItem(itemRequest);

        if (isNull(itemRequest.productId())) {
            groupService.existsById(itemRequest.groupId());

            final var listItem = new ShoppingListItem(
                shoppingList,
                itemRequest.groupId(),
                null,
                itemRequest.quantity(),
                itemRequest.unit()
            );

            shoppingListItemRepository.save(listItem);
        } else {
            productService.existsById(itemRequest.productId());

            final var listItem = new ShoppingListItem(
                shoppingList,
                null,
                itemRequest.productId(),
                itemRequest.quantity(),
                itemRequest.unit()
            );

            shoppingListItemRepository.save(listItem);
        }

    }

    public void create(final String name) {
        shoppingListRepository.save(new ShoppingList(name));
    }

    public void update(final UUID id, final String name) {
        final var shoppingList = shoppingListRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Shopping list not found")
        );

        shoppingList.setName(name);
        shoppingListRepository.save(shoppingList);
    }

    private void validateListItem(ShoppingListItemRequest itemRequest) {
        if (isNull(itemRequest.groupId()) && isNull(itemRequest.productId())) {
            throw new SchemaViolationException("Group ID or Product ID must be provided");
        }
    }

    public void removeItem(final UUID id, final UUID itemId) {
        shoppingListItemRepository.deleteByShoppingListIdAndId(id, itemId);
    }


    public List<ShoppingListResponse> listAllShoppingLists() {
        return shoppingListRepository.findAll().stream()
                .map(sl -> new ShoppingListResponse(sl.getId(), sl.getName()))
                .toList();
    }

    public List<ShoppingListItemResponse> listAllShoppingListItems(final UUID id) {
        return shoppingListItemRepository.findAllByShoppingListId(id);
    }
}
