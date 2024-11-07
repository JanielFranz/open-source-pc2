package com.aventon.platform.u201912668.crm.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record ProductId(Long productId) {
    public ProductId {
        if(productId == null || productId < 0) {
            throw new IllegalArgumentException("Invalid product id");
        }
    }
}
