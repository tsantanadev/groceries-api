package com.tsantana.groceries_api.controllers;

import com.tsantana.groceries_api.exceptions.ErrorMessage;
import com.tsantana.groceries_api.models.Brand;
import com.tsantana.groceries_api.services.BrandService;
import com.tsantana.groceries_api.vos.BrandRequest;
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
import java.util.List;
import java.util.UUID;

@Tag(name = "Brands", description = "Endpoints for managing brands")
@AllArgsConstructor
@RestController
@RequestMapping("/brands")
public class BrandController {

    private final BrandService service;

    @PostMapping
    @Operation(
            summary = "Create a new brand",
            description = "Creates a new brand and returns the created brand with its details.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Brand object to be created",
                    required = true,
                    content = @Content(schema = @Schema(implementation = BrandRequest.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successfully created brand"),
                    @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content(schema = @Schema(implementation = ErrorMessage.class))),
            }
    )
    public ResponseEntity<Brand> post(@Valid @RequestBody BrandRequest brandRequest) {
        final var brand = service.create(brandRequest);
        return ResponseEntity.created(URI.create("/brands/" + brand.getId())).body(brand);
    }

    @GetMapping
    @Operation(
            summary = "Retrieve a brand by name",
            description = "Finds a brand by its name and returns the brand details.",
            parameters = {
                    @Parameter(
                            name = "name",
                            description = "Name of the brand to retrieve",
                            required = true
                    )
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved brand")
            }
    )
    public ResponseEntity<List<Brand>> get(@RequestParam String name) {
        return ResponseEntity.ok(service.findByName(name));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update a brand",
            description = "Updates the details of an existing brand by its ID.",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "UUID of the brand to update",
                            required = true
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Updated brand object",
                    required = true,
                    content = @Content(schema = @Schema(implementation = BrandRequest.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully updated brand"),
                    @ApiResponse(responseCode = "404", description = "Brand not found", content = @Content(schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content(schema = @Schema(implementation = ErrorMessage.class))),
            }
    )
    public ResponseEntity<Brand> put(@Valid @RequestBody BrandRequest brandRequest, @PathVariable UUID id) {
        final var brand = service.update(brandRequest, id);
        return ResponseEntity.ok(brand);
    }
}
