package com.aventon.platform.u201912668.crm.infrastructure.persistence.jpa.repository;

import com.aventon.platform.u201912668.crm.domain.model.aggregates.Rating;
import com.aventon.platform.u201912668.crm.domain.model.valueobjects.EmailAddress;
import com.aventon.platform.u201912668.crm.domain.model.valueobjects.ProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    boolean existsByProductIdAndEmailAddress(ProductId productId, EmailAddress emailAddress);
    List<Rating> findAllByProductIdAndEmailAddress(ProductId productId, EmailAddress emailAddress);
}
