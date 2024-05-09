package com.github.brucemelo.bookreservation.service.exceptions;

import com.github.brucemelo.bookreservation.infrastructure.BadRequestException;

public class BookReviewedByUser extends BadRequestException {

    public BookReviewedByUser() {
        super("Book has been reviewed by user.");
    }

}
