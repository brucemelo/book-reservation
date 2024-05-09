package com.github.brucemelo.bookreservation.web;

import com.github.brucemelo.bookreservation.domain.Review;
import com.github.brucemelo.bookreservation.infrastructure.AppMappers;
import com.github.brucemelo.bookreservation.service.ReviewService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

import java.util.List;


@Controller("/reviews")
public class ReviewController {

    @Inject
    private ReviewService reviewService;

    @Post
    public HttpResponse<Review> create(@Body @Valid Model.NewReview newReview) {
        var review = AppMappers.INSTANCE.toReview(newReview);
        var createdReview = reviewService.create(review);
        return HttpResponse.created(createdReview);
    }

    @Get
    public HttpResponse<List<Review>> findAll() {
        var reviews = reviewService.findAll();
        return HttpResponse.ok(reviews);
    }

}
