package com.tsantana.groceries_api.controllers;

import com.tsantana.groceries_api.exceptions.ErrorMessage;
import com.tsantana.groceries_api.models.Product;
import com.tsantana.groceries_api.services.ProductService;
import com.tsantana.groceries_api.vos.ProductRequest;
import com.tsantana.groceries_api.vos.ProductUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@Tag(name = "Products", description = "Endpoints for managing products")
@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    @PostMapping
    @Operation(
            summary = "Create a new product",
            description = "Creates a new product and returns the created product with its details.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Product object to be created",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ProductRequest.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successfully created product"),
                    @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content(schema = @Schema(implementation = ErrorMessage.class))),
            }
    )
    public ResponseEntity<Product> post(@Valid @RequestBody ProductRequest productRequest) {
        final var product = service.create(productRequest);
        return ResponseEntity.created(URI.create("/products/" + product.getId())).body(product);
    }

    @GetMapping
    @Operation(
            summary = "Retrieve a product by name",
            description = "Finds a product by its name and returns the product details.",
            parameters = {
                    @Parameter(
                            name = "name",
                            description = "Name of the product to retrieve",
                            required = true
                    )
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved product")
            }
    )
    public ResponseEntity<Product> get(@RequestParam String name) {
        return ResponseEntity.ok(service.findByName(name));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update a product",
            description = "Updates the details of an existing product by its ID.",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "UUID of the product to update",
                            required = true
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Updated product object",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ProductUpdateRequest.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully updated product"),
                    @ApiResponse(responseCode = "404", description = "Product not found", content = @Content(schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content(schema = @Schema(implementation = ErrorMessage.class))),
            }
    )
    public ResponseEntity<Product> put(@Valid @RequestBody ProductUpdateRequest productRequest, @PathVariable UUID id) {
        final var product = service.update(productRequest, id);
        return ResponseEntity.ok(product);
    }
}
