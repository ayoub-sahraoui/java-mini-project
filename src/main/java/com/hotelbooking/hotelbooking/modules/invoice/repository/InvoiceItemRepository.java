package com.hotelbooking.hotelbooking.modules.invoice.repository;

import com.hotelbooking.hotelbooking.modules.invoice.model.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {
}
