package com.github.brucemelo.bookreservation.service.exceptions;

import com.github.brucemelo.bookreservation.infrastructure.BadRequestException;

public class BookNotAvailable extends BadRequestException {

    public BookNotAvailable() {
        super("Book not available.");
    }

}
