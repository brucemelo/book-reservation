package com.github.brucemelo.bookreservation.domain;

import io.micronaut.serde.annotation.Serdeable;
import io.soabase.recordbuilder.core.RecordBuilder;

import java.time.LocalDate;
import java.util.Optional;

@Serdeable
@RecordBuilder
public record BookReservation(
        Integer id,
        Book book,
        User user,
        LocalDate reservationDate,
        LocalDate dueDate,
        Integer status
) {

    public LocalDate defaultDueData() {
        return Optional.ofNullable(reservationDate)
                .map(date -> date.plusDays(3))
                .orElse(null);
    }

}