package com.companyname.payment.service.inteface;

import com.companyname.payment.model.payment.Invoice;
import com.companyname.payment.model.payment.PaymentResponse;
import com.companyname.payment.model.payment.Transactions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPaymentService {
    PaymentResponse createPayment(Invoice invoice);

    Page<Transactions> getTransactionsByInvoiceId(Long invoiceId, Pageable pageable);
}
