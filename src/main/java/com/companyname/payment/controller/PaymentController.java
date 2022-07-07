package com.companyname.payment.controller;

import com.companyname.payment.model.payment.Invoice;
import com.companyname.payment.model.payment.PaymentCriteria;
import com.companyname.payment.model.payment.PaymentResponse;
import com.companyname.payment.model.payment.Transactions;
import com.companyname.payment.service.inteface.IPaymentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@Tag(name = "Payments")
@RestController
@RequiredArgsConstructor
public class PaymentController {

    final IPaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody @Valid final Invoice invoice) {
        return new ResponseEntity(paymentService.createPayment(invoice), HttpStatus.CREATED);
    }

    @GetMapping("/transactions/{invoiceId}")
    public ResponseEntity<Page<Transactions>> getPayments(
            @PathVariable("invoiceId") final Long invoiceId,
            @ParameterObject final PaymentCriteria paymentCriteria) {
        return ResponseEntity.ok(paymentService.getTransactionsByInvoiceId(invoiceId, PageRequest.of(paymentCriteria.getPage(), paymentCriteria.getSize())));
    }
}
