package com.github.brucemelo.bookreservation.infrastructure;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }

}
