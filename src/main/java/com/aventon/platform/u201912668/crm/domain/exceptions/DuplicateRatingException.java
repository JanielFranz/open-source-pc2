package com.aventon.platform.u201912668.crm.domain.exceptions;

import com.aventon.platform.u201912668.crm.domain.model.valueobjects.ProductId;

public class DuplicateRatingException extends RuntimeException {
    public DuplicateRatingException(ProductId productId, Integer rating) {
        super("The actual product: %s and the the email %s has an rating created".formatted(productId.productId(), rating));
    }
}
