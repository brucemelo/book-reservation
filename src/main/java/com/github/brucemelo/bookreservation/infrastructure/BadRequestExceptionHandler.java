package com.github.brucemelo.bookreservation.infrastructure;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Produces
@Singleton
@Requires(classes = {BadRequestException.class})
public class BadRequestExceptionHandler implements ExceptionHandler<BadRequestException, HttpResponse<String>> {

    private static final Logger logger = LoggerFactory.getLogger(BadRequestExceptionHandler.class);

    @Override
    public HttpResponse<String> handle(HttpRequest request, BadRequestException exception) {
        logger.error(exception.getMessage(), exception);
        return HttpResponse.badRequest().body(exception.getMessage());
    }

}
