package com.aventon.platform.u201912668.crm.application.internal.commandservices;

import com.aventon.platform.u201912668.crm.domain.exceptions.DuplicateRatingException;
import com.aventon.platform.u201912668.crm.domain.model.aggregates.Rating;
import com.aventon.platform.u201912668.crm.domain.model.commands.CreateRatingCommand;
import com.aventon.platform.u201912668.crm.domain.model.valueobjects.EmailAddress;
import com.aventon.platform.u201912668.crm.domain.model.valueobjects.ProductId;
import com.aventon.platform.u201912668.crm.domain.services.RatingCommandService;
import com.aventon.platform.u201912668.crm.infrastructure.persistence.jpa.repository.RatingRepository;
import org.springframework.stereotype.Service;

@Service
public class RatingCommandServiceImpl implements RatingCommandService {

    private final RatingRepository ratingRepository;

    public RatingCommandServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public Long handle(CreateRatingCommand command) {

        var productId = new ProductId(command.productId());
        var emailAddress = new EmailAddress(command.emailAddress());
        var rating = new Rating(command);

        if(ratingRepository.existsByProductIdAndEmailAddress(productId, emailAddress)){
            var ratings = ratingRepository.findAllByProductIdAndEmailAddress(productId, emailAddress);
            ratings.forEach(r -> {
                boolean hasSameRating = r.isSameRating(command.rating());
                if(hasSameRating){
                    throw new DuplicateRatingException(productId, command.rating());
                }
            });
        }

        try{
            ratingRepository.save(rating);
            return rating.getId();
        } catch(Exception e){
            throw new IllegalStateException("An error occurred while creating the rating: %s".formatted(e.getMessage()));
        }
    }
}
