package com.github.brucemelo.bookreservation.domain;

public enum BookStatus {

    Available(1, "Available"),
    Unavailable(0, "Unavailable");

    private final Integer code;
    private final String description;

    BookStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static BookStatus fromCode(Integer code) {
        for (BookStatus bookStatus : BookStatus.values()) {
            if (bookStatus.getCode().equals(code)) {
                return bookStatus;
            }
        }
        throw new IllegalArgumentException("Invalid book status code: " + code);
    }

}
