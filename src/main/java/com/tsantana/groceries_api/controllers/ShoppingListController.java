package com.tsantana.groceries_api.controllers;

import com.tsantana.groceries_api.services.ShoppingListService;
import com.tsantana.groceries_api.vos.ShoppingListItemRequest;
import com.tsantana.groceries_api.vos.ShoppingListItemResponse;
import com.tsantana.groceries_api.vos.ShoppingListRequest;
import com.tsantana.groceries_api.vos.ShoppingListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Shopping List", description = "Endpoints for managing shopping lists")
@AllArgsConstructor
@RestController
@RequestMapping("/shopping-lists")
public class ShoppingListController {

    private final ShoppingListService service;

    @PostMapping
    @Operation(
            summary = "Create a new shopping list",
            description = "Creates a new shopping list",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Shopping list object to be created",
                    required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ShoppingListRequest.class))
            ),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Successfully created shopping list"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input data"),
            }
    )
    public ResponseEntity<Void> post(@Valid @RequestBody ShoppingListRequest shoppingListRequest) {
        service.create(shoppingListRequest.name());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update a shopping list",
            description = "Updates a shopping list",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "ID of the shopping list to update",
                            required = true
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Shopping list object to be updated",
                    required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ShoppingListRequest.class))
            ),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Successfully updated shopping list"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input data"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Shopping list not found"),
            }
    )
    public ResponseEntity<Void> put(@PathVariable UUID id, @Valid @RequestBody ShoppingListRequest shoppingListRequest) {
        service.update(id, shoppingListRequest.name());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/items")
    @Operation(
            summary = "Add an item to a shopping list",
            description = "Adds an item to a shopping list",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "ID of the shopping list to add the item",
                            required = true
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Item object to be added",
                    required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ShoppingListItemRequest.class))
            ),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Successfully added item to shopping list"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input data"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Shopping list not found"),
            }
    )
    public ResponseEntity<Void> postItem(@PathVariable UUID id, @Valid @RequestBody ShoppingListItemRequest itemRequest) {
        service.addItem(id, itemRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/items/{itemId}")
    @Operation(
            summary = "Remove an item from a shopping list",
            description = "Removes an item from a shopping list",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "ID of the shopping list to remove the item",
                            required = true
                    ),
                    @Parameter(
                            name = "itemId",
                            description = "ID of the item to be remove  d",
                            required = true
                    )
            },
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Successfully removed item from shopping list"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Shopping list or item not found"),
            }
    )
    public ResponseEntity<Void> deleteItem(@PathVariable UUID id, @PathVariable UUID itemId) {
        service.removeItem(id, itemId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(
            summary = "List shopping lists",
            description = "Lists all shopping lists",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved shopping list"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Shopping list not found"),
            }
    )
    public ResponseEntity<List<ShoppingListResponse>> get() {
        return ResponseEntity.ok(service.listAllShoppingLists());
    }

    @GetMapping("/{id}/items")
    @Operation(
            summary = "List items from a shopping list",
            description = "Lists all items from a shopping list",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "ID of the shopping list to list the items",
                            required = true
                    )
            },
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved items from shopping list"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Shopping list not found"),
            }
    )
    public ResponseEntity<List<ShoppingListItemResponse>> getItems(@PathVariable UUID id) {
        return ResponseEntity.ok(service.listAllShoppingListItems(id));
    }
}
