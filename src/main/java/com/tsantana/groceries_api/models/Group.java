package com.tsantana.groceries_api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "product_group")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Group {

        public Group(String name) {
                this.name = name;
                this.createdAt = LocalDateTime.now();
                this.updatedAt = LocalDateTime.now();
        }

        public Group(UUID groupId) {
                this.id = groupId;
        }

        @Id
        @GeneratedValue(generator = "UUID")
        @Column(name = "id", updatable = false, nullable = false)
        private UUID id;

        @Column(name = "name", nullable = false)
        private String name;

        @Column(name = "created_at", nullable = false)
        private LocalDateTime createdAt;

        @Column(name = "updated_at", nullable = false)
        private LocalDateTime updatedAt;
}