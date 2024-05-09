package com.github.brucemelo.bookreservation.service;

import com.github.brucemelo.bookreservation.domain.BookReservation;
import com.github.brucemelo.bookreservation.domain.BookStatus;
import com.github.brucemelo.bookreservation.infrastructure.AppMappers;
import com.github.brucemelo.bookreservation.service.exceptions.BookNotAvailable;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.jooq.DSLContext;

import static com.github.brucemelo.jooq.generated.Tables.BOOK;
import static com.github.brucemelo.jooq.generated.Tables.RESERVATION;


@Singleton
public class ReservationService {

    @Inject
    private DSLContext dsl;

    public BookReservation makeReservation(BookReservation bookReservation) {
        dsl.transaction(tx -> {
            var book = tx.dsl().selectFrom(BOOK)
                    .where(BOOK.ID.eq(bookReservation.book().id()))
                    .and(BOOK.STATUS.eq(BookStatus.Available)).fetchOptional().orElseThrow(BookNotAvailable::new);
            book.setStatus(BookStatus.Unavailable);
            book.update();
            tx.dsl().insertInto(RESERVATION,
                            RESERVATION.BOOK_ID,
                            RESERVATION.USER_ID,
                            RESERVATION.RESERVATION_DATE,
                            RESERVATION.DUE_DATE)
                    .values(bookReservation.book().id(),
                            bookReservation.user().id(),
                            bookReservation.reservationDate(),
                            bookReservation.defaultDueData()).execute();
        });
        var reservationRecord = dsl.selectFrom(RESERVATION)
                .where(RESERVATION.BOOK_ID.eq(bookReservation.book().id()))
                .and(RESERVATION.USER_ID.eq(bookReservation.user().id()))
                .fetchOne();
        return AppMappers.INSTANCE.toBookReservation(reservationRecord);
    }

}
