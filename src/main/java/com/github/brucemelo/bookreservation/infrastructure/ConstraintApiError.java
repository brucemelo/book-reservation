package com.github.brucemelo.bookreservation.infrastructure;


import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Serdeable
public record ConstraintApiError(Integer code, String message, List<Field> violations) {

    @Serdeable
    public record Field(String field, String message) {}

}
