package com.github.brucemelo.bookreservation.web;

import com.github.brucemelo.bookreservation.domain.BookReservation;
import com.github.brucemelo.bookreservation.infrastructure.AppMappers;
import com.github.brucemelo.bookreservation.service.ReservationService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;
import jakarta.validation.Valid;


@Controller("/reservations")
public class ReservationController {

    @Inject
    private ReservationService reservationService;

    @Post
    public HttpResponse<BookReservation> makeReservation(@Body @Valid Model.NewReservation newReservation) {
        var bookReservation = AppMappers.INSTANCE.toBookReservation(newReservation);
        var createdReservation = reservationService.makeReservation(bookReservation);
        return HttpResponse.created(createdReservation);
    }

}
