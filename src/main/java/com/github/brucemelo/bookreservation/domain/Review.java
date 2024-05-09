package com.github.brucemelo.bookreservation.domain;

import io.micronaut.serde.annotation.Serdeable;
import io.soabase.recordbuilder.core.RecordBuilder;

import java.time.LocalDateTime;

@Serdeable
@RecordBuilder
public record Review(
        Integer id,
        Book book,
        User user,
        Integer score,
        String comment,
        LocalDateTime reviewDate
) {}
