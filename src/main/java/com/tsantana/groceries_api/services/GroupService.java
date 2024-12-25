package com.tsantana.groceries_api.services;

import com.tsantana.groceries_api.exceptions.NotFoundException;
import com.tsantana.groceries_api.exceptions.SchemaViolationException;
import com.tsantana.groceries_api.models.Group;
import com.tsantana.groceries_api.repositories.GroupRepository;
import com.tsantana.groceries_api.vos.GroupRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GroupService {

    private final GroupRepository repository;

    public Group create(final GroupRequest groupRequest) {
        final var exists = repository.existsByName(groupRequest.name());
        if (exists) {
            throw new SchemaViolationException(String.format("Group with name %s already exists", groupRequest.name()));
        }

        final var group = new Group(groupRequest.name());
        return repository.save(group);
    }

    public Group update(final GroupRequest groupRequest, final UUID id) {
        final var group = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Group not found")
        );

        group.setName(groupRequest.name());
        group.setUpdatedAt(LocalDateTime.now());
        return repository.save(group);
    }

    public Group findByName(final String name) {
        return repository.findByName(name).orElseThrow(
                () -> new NotFoundException("Group not found")
        );
    }

    public Boolean existsById(final UUID id) {
        return repository.existsById(id);
    }
}
