package com.aventon.platform.u201912668.crm.domain.services;

import com.aventon.platform.u201912668.crm.domain.model.commands.CreateRatingCommand;

public interface RatingCommandService {
    Long handle(CreateRatingCommand command);
}
