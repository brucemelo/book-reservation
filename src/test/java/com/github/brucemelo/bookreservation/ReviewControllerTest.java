package com.github.brucemelo.bookreservation;

import com.github.brucemelo.bookreservation.domain.Review;
import com.github.brucemelo.bookreservation.web.Model;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@MicronautTest
class ReviewControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    @DisplayName("Should create new book review and list reviews")
    public void testReview() {
        var newReview = new Model.NewReview( 1, 1, 5, "Excellent!");
        var createResponse = client.toBlocking().exchange(HttpRequest.POST("api/reviews", newReview), Review.class);
        assertEquals(HttpStatus.CREATED, createResponse.getStatus());
        var createdReview = createResponse.body();
        assertNotNull(createdReview.id());

        var readResponse = client.toBlocking().exchange(HttpRequest.GET("api/reviews/"), Argument.listOf(Review.class));
        assertEquals(HttpStatus.OK, readResponse.getStatus());
    }

}