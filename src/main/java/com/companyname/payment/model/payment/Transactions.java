package com.companyname.payment.model.payment;

import com.companyname.payment.model.enums.TransactionStatus;

import java.math.BigDecimal;

public record Transactions(
        TransactionStatus status,
        String uuid,
        BigDecimal amount,
        Long invoiceId
) {
}
