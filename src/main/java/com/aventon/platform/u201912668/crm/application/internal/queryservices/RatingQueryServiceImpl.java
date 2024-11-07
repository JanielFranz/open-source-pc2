package com.aventon.platform.u201912668.crm.application.internal.queryservices;

import com.aventon.platform.u201912668.crm.domain.model.aggregates.Rating;
import com.aventon.platform.u201912668.crm.domain.model.queries.GetRatingByIdQuery;
import com.aventon.platform.u201912668.crm.domain.services.RatingQueryService;
import com.aventon.platform.u201912668.crm.infrastructure.persistence.jpa.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingQueryServiceImpl implements RatingQueryService {
    private final RatingRepository ratingRepository;

    public RatingQueryServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public Optional<Rating> handle(GetRatingByIdQuery query) {
        return ratingRepository.findById(query.ratingId());
    }
}
