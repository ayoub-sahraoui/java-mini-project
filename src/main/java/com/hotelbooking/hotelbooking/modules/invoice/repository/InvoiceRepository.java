package com.hotelbooking.hotelbooking.modules.invoice.repository;

import com.hotelbooking.hotelbooking.modules.invoice.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
