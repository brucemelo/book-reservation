package com.github.brucemelo.bookreservation;

import com.github.brucemelo.bookreservation.domain.BookReservation;
import com.github.brucemelo.bookreservation.infrastructure.ConstraintApiError;
import com.github.brucemelo.bookreservation.web.Model;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.serde.ObjectMapper;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@MicronautTest
class BookReservationControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    @DisplayName("Should make new book reservation")
    public void testBookReservation() {
        var newReservation = new Model.NewReservation(1, 1, LocalDate.now());
        var createResponse = client.toBlocking().exchange(HttpRequest.POST("api/reservations", newReservation), BookReservation.class);
        assertEquals(HttpStatus.CREATED, createResponse.getStatus());
        var createdReservation = createResponse.body();
        assertNotNull(createdReservation.id());
    }

    @Test
    @DisplayName("Should try to make new book reservation with invalid data")
    public void testMakeReservationInvalidData(ObjectMapper objectMapper) throws IOException {
        var json = """
            {
                "bookId": 1,
                "userId": 0,
                "reservationDate": "%s"
            }
            """.formatted(LocalDate.now().toString());

        var response = assertThrows(HttpClientResponseException.class, () -> {
            client.toBlocking().exchange(HttpRequest.POST("api/reservations", json), String.class);
        });
        var constraintApiError = objectMapper.readValue(response.getResponse().body().toString(), ConstraintApiError.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
        var field = constraintApiError.violations().stream().findFirst().get();
        assertEquals(field.field(), "makeReservation.userId");
        assertEquals(field.message(), "must be greater than 0");
    }

}