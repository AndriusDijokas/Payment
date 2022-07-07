package com.companyname.payment.jpa.repository;

import com.companyname.payment.jpa.entity.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity,Long> {

    Page<TransactionEntity> findByInvoiceId(Long invoiceId, Pageable pageable);
    Page<TransactionEntity> findAll(Pageable pageable);

}
