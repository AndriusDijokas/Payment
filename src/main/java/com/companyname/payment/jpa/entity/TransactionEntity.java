package com.companyname.payment.jpa.entity;

import com.companyname.payment.model.enums.Currency;
import com.companyname.payment.model.enums.TransactionStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder

@Entity
@Table(name = "transactions")
public class TransactionEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    TransactionStatus status;
    BigDecimal amount;
    @Enumerated(EnumType.STRING)
    Currency currency;

    String userUuid;
    String cardUuid;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    InvoiceEntity invoice;
}
