package com.aventon.platform.u201912668.crm.domain.services;

import com.aventon.platform.u201912668.crm.domain.model.aggregates.Rating;
import com.aventon.platform.u201912668.crm.domain.model.queries.GetRatingByIdQuery;

import java.util.Optional;

public interface RatingQueryService {
    Optional<Rating> handle(GetRatingByIdQuery query);
}
