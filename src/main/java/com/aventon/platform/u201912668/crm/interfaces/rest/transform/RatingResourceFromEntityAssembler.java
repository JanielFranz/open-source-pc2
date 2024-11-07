package com.aventon.platform.u201912668.crm.interfaces.rest.transform;

import com.aventon.platform.u201912668.crm.domain.model.aggregates.Rating;
import com.aventon.platform.u201912668.crm.interfaces.rest.resource.RatingResource;

public class RatingResourceFromEntityAssembler {
    public static RatingResource toResourceFromEntity(Rating entity) {
        return new RatingResource(entity.getId(), entity.getProductIdAsLong(), entity.getComment());
    }
}
