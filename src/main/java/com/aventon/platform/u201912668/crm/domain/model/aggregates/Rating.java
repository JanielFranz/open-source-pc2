package com.aventon.platform.u201912668.crm.domain.model.aggregates;

import com.aventon.platform.u201912668.crm.domain.model.commands.CreateRatingCommand;
import com.aventon.platform.u201912668.crm.domain.model.valueobjects.EmailAddress;
import com.aventon.platform.u201912668.crm.domain.model.valueobjects.ProductId;
import com.aventon.platform.u201912668.crm.domain.model.valueobjects.RatingAspect;
import com.aventon.platform.u201912668.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class Rating extends AuditableAbstractAggregateRoot<Rating> {

    @Embedded
    private ProductId productId;

    @Size(max = 50)
    @NotNull
    @Embedded
    private EmailAddress emailAddress;

    @Transient
    private Integer rating;


    @NotNull
    @Enumerated(EnumType.STRING)
    private RatingAspect ratingAspect;

    @Size(max = 50)
    @Column(length = 50)
    private String comment;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    public Date ratedAt;

    public Rating(Long productId, EmailAddress emailAddress, Integer rating, String comment) {
        this.productId = new ProductId(productId);
        this.emailAddress = emailAddress;
        this.ratingAspect = this.transformRatingToRatingAspect(rating);
        this.comment = comment;
    }

    public Rating(CreateRatingCommand command) {
        this.productId = new ProductId(command.productId());
        this.emailAddress = new EmailAddress(command.emailAddress());
        this.ratingAspect = this.transformRatingToRatingAspect(command.rating());
        this.comment = command.comment();
    }


    public RatingAspect transformRatingToRatingAspect(Integer rating) {
        return switch (rating) {
            case 1 -> RatingAspect.EASE_OF_USE;
            case 2 -> RatingAspect.DESIGN;
            case 3 -> RatingAspect.SETTING;
            case 4 -> RatingAspect.PRICE;
            case 5 -> RatingAspect.SUPPORT;
            case 6 -> RatingAspect.OVERALL_QUALITY;
            default -> throw new IllegalArgumentException("Invalid rating: %s. Rating must be between 1 - 6");
        };
    }

    public Long getProductIdAsLong() {
        return this.productId.productId();
    }

    public boolean isSameRating(Integer rating) {
        RatingAspect temporalRatingAspect = transformRatingToRatingAspect(rating);
        return this.ratingAspect.equals(temporalRatingAspect);
    }
}
