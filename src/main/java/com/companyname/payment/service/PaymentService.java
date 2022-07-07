package com.companyname.payment.service;

import com.companyname.payment.controller.exception.ErrorStatus;
import com.companyname.payment.jpa.entity.InvoiceEntity;
import com.companyname.payment.jpa.entity.TransactionEntity;
import com.companyname.payment.jpa.repository.InvoiceRepository;
import com.companyname.payment.jpa.repository.TransactionRepository;
import com.companyname.payment.model.enums.TransactionStatus;
import com.companyname.payment.model.payment.Invoice;
import com.companyname.payment.model.payment.PaymentResponse;
import com.companyname.payment.model.payment.Transactions;
import com.companyname.payment.service.inteface.ICardService;
import com.companyname.payment.service.inteface.IPaymentService;
import com.companyname.payment.service.inteface.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService implements IPaymentService {

    final InvoiceRepository invoiceRepository;
    final TransactionRepository transactionRepository;
    final IUserService userService;
    final ICardService cardService;

    @Override
    public PaymentResponse createPayment(Invoice invoice) {

        var invoiceEntity = invoiceRepository.findByInvoiceId(invoice.invoiceId()).orElseGet(() -> invoiceRepository.save(InvoiceEntity.builder().invoiceId(invoice.invoiceId()).build()));
        var userEntity = userService.createIfNotExists(invoice.cardHolder());
        var cardEntity = cardService.createIfNotExists(userEntity, invoice.card());

        var transaction =
                TransactionEntity.builder()
                        .amount(invoice.amount())
                        .invoice(invoiceEntity)
                        .status(TransactionStatus.APPROVED)
                        .uuid(UUID.randomUUID().toString())
                        .invoice(invoiceEntity)
                        .currency(invoice.currency())
                        .cardUuid(cardEntity.getUuid())
                        .userUuid(userEntity.getUuid())
                        .build();

        transactionRepository.save(transaction);
        return new PaymentResponse(true);
    }

    @Override
    public Page<Transactions> getTransactionsByInvoiceId(Long invoiceId, Pageable pageable) {
        var invoice = invoiceRepository.findByInvoiceId(invoiceId).orElseThrow(ErrorStatus.NO_INVOICE_FOUND::getException);

        return transactionRepository.findByInvoiceId(invoice.getId(), pageable).map(entity ->
                new Transactions(entity.getStatus(),
                        entity.getUuid(),
                        entity.getAmount(),
                        invoice.getInvoiceId()
                ));
    }
}
