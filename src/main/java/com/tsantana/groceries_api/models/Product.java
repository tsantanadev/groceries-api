package com.tsantana.groceries_api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

        @Id
        @GeneratedValue(generator = "UUID")
        @Column(name = "id", updatable = false, nullable = false)
        private UUID id;

        @Column(name = "name", nullable = false)
        private String name;

        @Column(name = "gtin", nullable = false)
        private String gtin;

        @Column(name = "group_id")
        private UUID groupId;

        @Column(name = "brand_id")
        private UUID brandId;

        @Column(name = "best_price", nullable = false)
        private BigDecimal bestPrice;

        @Column(name = "created_at", nullable = false)
        private LocalDateTime createdAt;

        @Column(name = "updated_at", nullable = false)
        private LocalDateTime updatedAt;

        public Product(final String name, final String gtin, final UUID groupId, final UUID brandId, BigDecimal price) {
                this.name = name;
                this.gtin = gtin;
                this.groupId = groupId;
                this.brandId = brandId;
                this.bestPrice = price;
                this.createdAt = LocalDateTime.now();
                this.updatedAt = LocalDateTime.now();
        }
}
