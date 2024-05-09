package com.github.brucemelo.bookreservation;

import com.github.brucemelo.bookreservation.domain.Book;
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@MicronautTest
class BookControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    @DisplayName("Should search books by title and author")
    public void testSearchBooksByTitleAndAuthor() {
        var request = HttpRequest.GET("api/books/search?title=Hobbit&author=Tolkien");
        var response = client.toBlocking().exchange(request, Argument.listOf(Book.class));

        assertEquals(HttpStatus.OK, response.getStatus());
        assertFalse(response.body().isEmpty(), "The list of books should not be empty.");
        response.body().forEach(book -> {
            assertTrue(book.title().contains("Hobbit"), "Book title should contain 'Hobbit'.");
            assertTrue(book.author().contains("Tolkien"), "Book author should be 'Tolkien'.");
        });
    }

    @Test
    @DisplayName("Should search books by title")
    public void testSearchBooksByTitle() {
        var request = HttpRequest.GET("api/books/search?title=Potter");
        var response = client.toBlocking().exchange(request, Argument.listOf(Book.class));

        assertEquals(HttpStatus.OK, response.getStatus());
        assertTrue(response.body().isEmpty(), "The list of books should be empty.");
    }

}
