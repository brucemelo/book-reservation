package com.github.brucemelo.bookreservation.domain;

import io.micronaut.serde.annotation.Serdeable;
import io.soabase.recordbuilder.core.RecordBuilder;

@Serdeable
@RecordBuilder
public record Book(
        Integer id,
        String title,
        String author,
        String isbn,
        Integer publishedYear,
        String genre,
        BookStatus status
) {
}
