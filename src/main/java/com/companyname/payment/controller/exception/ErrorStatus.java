package com.companyname.payment.controller.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public enum ErrorStatus {
    INCORRECT_REQUEST("INCORRECT_REQUEST"),

    NO_USER_FOUND("User not found"),
    NO_INVOICE_FOUND("Invoice not found");

    private final String message;

    public final GenericException getException() {
        return new GenericException(this);
    }

    public void throwException() {
        throw new GenericException(this);
    }
}
