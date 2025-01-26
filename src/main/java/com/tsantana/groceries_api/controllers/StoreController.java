package com.tsantana.groceries_api.controllers;

import com.tsantana.groceries_api.exceptions.ErrorMessage;
import com.tsantana.groceries_api.models.Store;
import com.tsantana.groceries_api.services.StoreService;
import com.tsantana.groceries_api.vos.StoreRequest;
import com.tsantana.groceries_api.vos.StoreResponse;
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

@RestController
@AllArgsConstructor
@RequestMapping("/stores")
@Tag(name = "Stores", description = "Endpoints for managing stores")
public class StoreController {

    private final StoreService service;

    @PostMapping
    @Operation(
            summary = "Create a new store",
            description = "Creates a new store and returns the created store with its details.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Store object to be created",
                    required = true,
                    content = @Content(schema = @Schema(implementation = StoreRequest.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successfully created store"),
                    @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content(schema = @Schema(implementation = ErrorMessage.class))),
            }
    )
    public ResponseEntity<StoreResponse> post(@Valid @RequestBody StoreRequest storeRequest) {
        final Store store = service.create(storeRequest);
        return ResponseEntity.created(URI.create("/stores/" + store.getId())).body(new StoreResponse(store));
    }

    @GetMapping
    @Operation(
            summary = "Retrieve a store by name or geolocation",
            description = "Finds a store and returns the store details.",
            parameters = {
                    @Parameter(
                            name = "name",
                            description = "Name of the store to retrieve"
                    ),
                    @Parameter(
                            name = "latitude",
                            description = "Latitude of the store to retrieve"
                    ),
                    @Parameter(
                            name = "longitude",
                            description = "Longitude of the store to retrieve"
                    )
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved store")
            }
    )
    public ResponseEntity<List<StoreResponse>> get(
            @RequestParam(required = false) String name,
            @RequestParam Double latitude,
            @RequestParam Double longitude,
            @RequestParam(required = false, defaultValue = "15000") Double radius){

        if (name != null) {
            final var stores = service.findByName(name, latitude, longitude, radius);
            return ResponseEntity.ok(stores);
        }

        final var stores = service.findByLocation(latitude, longitude, radius);
        return ResponseEntity.ok(stores);
    }
}
