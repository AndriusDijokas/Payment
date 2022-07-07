package com.companyname.payment.controller.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationErrorResponse extends ErrorResponse {
    private final List<FieldError> errors;
    private final Boolean approved;

    public ValidationErrorResponse(ErrorStatus errorStatus, Boolean approved, List<FieldError> fieldErrors) {
        super(errorStatus);
        this.errors = fieldErrors;
        this.approved = approved;
    }
}
