package com.eazybank.cards.constants;

import org.springframework.http.HttpStatus;

public final class CardContants {

    private CardContants() {
        throw new IllegalArgumentException("Cannot instance it");
    }

    public static final HttpStatus STATUS_201 = HttpStatus.CREATED;
    public static final String MESSAGE_201 = "Card created successfully";
    public static final HttpStatus STATUS_200 = HttpStatus.OK;
    public static final String MESSAGE_200 = "Request processed successfully";
    public static final HttpStatus STATUS_417 = HttpStatus.EXPECTATION_FAILED;
    public static final String MESSAGE_417_UPDATE = "Update operation failed. Please try again or contact Dev team";
    public static final String MESSAGE_417_DELETE = "Delete operation failed. Please try again or contact Dev team";
    public static final HttpStatus STATUS_500 = HttpStatus.INTERNAL_SERVER_ERROR;
    public static final String MESSAGE_500 = "An error occurred. Please try again or contact Dev team";
}
