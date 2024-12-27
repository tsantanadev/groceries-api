package com.tsantana.groceries_api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "price")
public class Price {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(name = "store_id", nullable = false)
    private UUID storeId;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "is_promotion", nullable = false)
    private Boolean isPromotion;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Price(final UUID productId,
                 final UUID storeId,
                 final BigDecimal price,
                 final Boolean isPromotion,
                 final LocalDateTime createdAt,
                 final LocalDateTime updatedAt) {
        this.productId = productId;
        this.storeId = storeId;
        this.price = price;
        this.isPromotion = isPromotion;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
