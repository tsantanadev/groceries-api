package com.tsantana.groceries_api.services;

import com.tsantana.groceries_api.models.Group;
import com.tsantana.groceries_api.repositories.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GroupService {

    private final GroupRepository repository;

    public Group create(Group group) {
        return repository.save(group);
    }

    public Group update(Group group) {
        return repository.save(group);
    }

    public Group findByName(String name) {
        return repository.findByName(name);
    }

}
