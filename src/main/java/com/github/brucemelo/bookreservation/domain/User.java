package com.github.brucemelo.bookreservation.domain;

import io.micronaut.serde.annotation.Serdeable;
import io.soabase.recordbuilder.core.RecordBuilder;

import java.time.OffsetDateTime;

@Serdeable
@RecordBuilder
public record User(
        Integer id,
        String username,
        String email,
        OffsetDateTime createdAt,
        OffsetDateTime lastUpdatedAt
) {}
