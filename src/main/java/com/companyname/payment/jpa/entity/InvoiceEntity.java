package com.companyname.payment.jpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder

@Entity
@Table(name ="invoices")
public class InvoiceEntity extends BaseEntity {

    @Column(unique = true)
    Long invoiceId;

    @OneToMany(mappedBy = "invoice")
    Set<TransactionEntity> transactions;

}
