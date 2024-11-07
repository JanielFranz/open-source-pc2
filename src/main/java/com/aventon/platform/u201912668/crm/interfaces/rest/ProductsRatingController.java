package com.aventon.platform.u201912668.crm.interfaces.rest;

import com.aventon.platform.u201912668.crm.domain.model.queries.GetRatingByIdQuery;
import com.aventon.platform.u201912668.crm.domain.services.RatingCommandService;
import com.aventon.platform.u201912668.crm.domain.services.RatingQueryService;
import com.aventon.platform.u201912668.crm.interfaces.rest.resource.CreateRatingResource;
import com.aventon.platform.u201912668.crm.interfaces.rest.resource.RatingResource;
import com.aventon.platform.u201912668.crm.interfaces.rest.transform.CreateRatingCommandFromResourceAssembler;
import com.aventon.platform.u201912668.crm.interfaces.rest.transform.RatingResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

//I RAN OUT OF TIME :C 





@RestController
@RequestMapping(value = "/api/v1/", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Ratings", description = "All product ratings related endpoints")
public class ProductsRatingController {
    private final RatingCommandService ratingCommandService;
    private final RatingQueryService ratingQueryService;


    public ProductsRatingController(RatingCommandService ratingCommandService, RatingQueryService ratingQueryService) {
        this.ratingCommandService = ratingCommandService;
        this.ratingQueryService = ratingQueryService;
    }

    @Operation(summary = "Create a new course")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Course created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Course not found")})
    @PostMapping("/{productId}/ratings")
    public ResponseEntity<RatingResource> createRating(@RequestBody CreateRatingResource createRatingResource, @PathVariable Long productId) {
        var createRatingCommand = CreateRatingCommandFromResourceAssembler.toCommandFromResource(productId, createRatingResource);
        var ratingId = ratingCommandService.handle(createRatingCommand);
        if(ratingId == null || ratingId == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        var getRatingByIdQuery = new GetRatingByIdQuery(ratingId);
        var rating = ratingQueryService.handle(getRatingByIdQuery);
        var ratingEntity = rating.get();
        var ratingResource = RatingResourceFromEntityAssembler.toResourceFromEntity(ratingEntity);
        return new ResponseEntity<>(ratingResource, HttpStatus.CREATED);

    }



}
