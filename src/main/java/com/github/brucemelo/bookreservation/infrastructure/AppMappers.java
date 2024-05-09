package com.github.brucemelo.bookreservation.infrastructure;

import com.github.brucemelo.bookreservation.domain.BookReservation;
import com.github.brucemelo.bookreservation.domain.Review;
import com.github.brucemelo.bookreservation.web.Model;
import com.github.brucemelo.jooq.generated.tables.records.ReservationRecord;
import com.github.brucemelo.jooq.generated.tables.records.ReviewRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AppMappers {

    AppMappers INSTANCE = Mappers.getMapper( AppMappers.class );

    @Mapping(source = "bookId", target = "book.id")
    @Mapping(source = "userId", target = "user.id")
    Review toReview(Model.NewReview newReview);

    @Mapping(source = "bookId", target = "book.id")
    @Mapping(source = "userId", target = "user.id")
    Review toReview(ReviewRecord reviewRecord);

    @Mapping(source = "bookId", target = "book.id")
    @Mapping(source = "userId", target = "user.id")
    BookReservation toBookReservation(Model.NewReservation newReservation);

    @Mapping(source = "bookId", target = "book.id")
    @Mapping(source = "userId", target = "user.id")
    BookReservation toBookReservation(ReservationRecord reservationRecord);

}
