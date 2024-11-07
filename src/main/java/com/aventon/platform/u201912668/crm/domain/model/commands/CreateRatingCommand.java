package com.aventon.platform.u201912668.crm.domain.model.commands;

import com.aventon.platform.u201912668.crm.domain.model.valueobjects.EmailAddress;

public record CreateRatingCommand(Long productId, String emailAddress, Integer rating, String comment) {

}
