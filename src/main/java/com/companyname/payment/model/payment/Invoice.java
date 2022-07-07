package com.companyname.payment.model.payment;

import com.companyname.payment.model.enums.Currency;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.math.BigDecimal;


public record Invoice(
        @Min(value = 0L, message = "The value must be positive")
        Long invoiceId,
        @Min(value = 0L, message = "The value must be positive")
        BigDecimal amount,
        //Todo validate enum
        Currency currency,
        @Valid
        User cardHolder,
        @Valid
        Card card
) {
}
