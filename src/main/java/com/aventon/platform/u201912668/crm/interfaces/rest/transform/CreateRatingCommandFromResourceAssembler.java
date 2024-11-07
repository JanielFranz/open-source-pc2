package com.aventon.platform.u201912668.crm.interfaces.rest.transform;

import com.aventon.platform.u201912668.crm.domain.model.commands.CreateRatingCommand;
import com.aventon.platform.u201912668.crm.interfaces.rest.resource.CreateRatingResource;

public class CreateRatingCommandFromResourceAssembler {
    public static CreateRatingCommand toCommandFromResource(Long productId, CreateRatingResource resource) {
        return new CreateRatingCommand(productId, resource.emailAddress(), resource.rating(), resource.comment());
    }
}
