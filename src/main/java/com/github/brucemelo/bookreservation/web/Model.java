package com.github.brucemelo.bookreservation.web;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public interface Model {

    @Serdeable
    record NewReservation(
            @Positive
            Integer bookId,
            @Positive
            Integer userId,
            @FutureOrPresent
            LocalDate reservationDate) { }

    @Serdeable
    record NewReview(
            @Positive
            Integer bookId,
            @Positive
            Integer userId,
            @Min(1) @Max(5)
            Integer score,
            String comment) {
    }

}
