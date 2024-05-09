package com.github.brucemelo.bookreservation.infrastructure;

import io.micronaut.context.annotation.Primary;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;
import jakarta.validation.ConstraintViolationException;

@Produces
@Singleton
@Primary
public class ConstraintViolationExceptionHandler implements ExceptionHandler<ConstraintViolationException, HttpResponse<ConstraintApiError>> {

    @Override
    public HttpResponse<ConstraintApiError> handle(HttpRequest request, ConstraintViolationException exception) {
        var list = exception.getConstraintViolations().stream()
                .map(constraintViolation ->
                {
                    var sb = new StringBuilder();
                    var iterator = constraintViolation.getPropertyPath().iterator();
                    iterator.next();
                    while (iterator.hasNext()) {
                        sb.append(iterator.next().getName());
                        if (iterator.hasNext()) {
                            sb.append(".");
                        }
                    }
                    return new ConstraintApiError.Field(sb.toString(), constraintViolation.getMessage());
                })
                .toList();
        var constraintApiError = new ConstraintApiError(HttpStatus.BAD_REQUEST.getCode(), HttpStatus.BAD_REQUEST.getReason(), list);
        return HttpResponse.status(HttpStatus.BAD_REQUEST).body(constraintApiError);
    }

}
