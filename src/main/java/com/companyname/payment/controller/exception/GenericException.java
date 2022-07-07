package com.companyname.payment.controller.exception;

import lombok.Getter;

@Getter
public class GenericException extends RuntimeException {
    private final String code;

    public GenericException(ErrorStatus errorStatus) {
        super(errorStatus.getMessage());
        code = errorStatus.name();
    }
}
