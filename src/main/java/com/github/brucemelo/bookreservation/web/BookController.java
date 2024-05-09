package com.github.brucemelo.bookreservation.web;

import com.github.brucemelo.bookreservation.domain.Book;
import com.github.brucemelo.bookreservation.service.BookService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@Controller("/books")
public class BookController {

    @Inject
    private BookService bookService;

    @Get("/search")
    public HttpResponse<List<Book>> searchBooks(
            @QueryValue Optional<String> title,
            @QueryValue Optional<String> author) {
        return HttpResponse.ok(bookService.findBooks(title, author));
    }

}
