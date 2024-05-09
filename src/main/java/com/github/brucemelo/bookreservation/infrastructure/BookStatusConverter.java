package com.github.brucemelo.bookreservation.infrastructure;

import com.github.brucemelo.bookreservation.domain.BookStatus;
import org.jooq.impl.AbstractConverter;

public class BookStatusConverter extends AbstractConverter<Integer, BookStatus> {

    public BookStatusConverter() {
        super(Integer.class, BookStatus.class);
    }

    @Override
    public BookStatus from(Integer databaseObject) {
        return BookStatus.fromCode(databaseObject);
    }

    @Override
    public Integer to(BookStatus userObject) {
        return userObject.getCode();
    }

}
