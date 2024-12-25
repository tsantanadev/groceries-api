package com.tsantana.groceries_api.controllers;

import com.tsantana.groceries_api.exceptions.ErrorMessage;
import com.tsantana.groceries_api.models.Group;
import com.tsantana.groceries_api.services.GroupService;
import com.tsantana.groceries_api.vos.GroupRequest;
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

@Tag(name = "Groups", description = "Endpoints for managing groups")
@AllArgsConstructor
@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService service;

    @PostMapping
    @Operation(
            summary = "Create a new group",
            description = "Creates a new group and returns the created group with its details.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Group object to be created",
                    required = true,
                    content = @Content(schema = @Schema(implementation = GroupRequest.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successfully created group"),
                    @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content(schema = @Schema(implementation = ErrorMessage.class))),
            }
    )
    public ResponseEntity<Group> post(@Valid @RequestBody GroupRequest groupRequest) {
        final var group = service.create(groupRequest);
        return ResponseEntity.created(URI.create("/groups/" + group.getId())).body(group);
    }

    @GetMapping
    @Operation(
            summary = "Retrieve a group by name",
            description = "Finds a group by its name and returns the group details.",
            parameters = {
                    @Parameter(
                            name = "name",
                            description = "Name of the group to retrieve",
                            required = true
                    )
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved group")
            }
    )
    public ResponseEntity<Group> get(@RequestParam String name) {
        return ResponseEntity.ok(service.findByName(name));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update a group",
            description = "Updates the details of an existing group by its ID.",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "UUID of the group to update",
                            required = true
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Updated group object",
                    required = true,
                    content = @Content(schema = @Schema(implementation = GroupRequest.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully updated group"),
                    @ApiResponse(responseCode = "404", description = "Group not found", content = @Content(schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content(schema = @Schema(implementation = ErrorMessage.class))),
            }
    )
    public ResponseEntity<Group> put(@Valid @RequestBody GroupRequest groupRequest, @PathVariable UUID id) {
        final var group = service.update(groupRequest, id);
        return ResponseEntity.ok(group);
    }
}
