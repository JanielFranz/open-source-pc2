package com.aventon.platform.u201912668.crm.interfaces.rest.resource;

import com.aventon.platform.u201912668.crm.domain.model.valueobjects.EmailAddress;

public record CreateRatingResource(String emailAddress, Integer rating, String comment) {
}
