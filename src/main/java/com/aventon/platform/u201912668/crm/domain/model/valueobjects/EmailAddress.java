package com.aventon.platform.u201912668.crm.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record EmailAddress(String email) {
}
