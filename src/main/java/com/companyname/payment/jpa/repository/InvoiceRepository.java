package com.companyname.payment.jpa.repository;

import com.companyname.payment.jpa.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity,Long> {

    boolean existsById(Long id);
    Optional<InvoiceEntity> findByInvoiceId(Long invoiceId);

}
