package com.hotelbooking.hotelbooking.modules.invoice.model;

import com.hotelbooking.hotelbooking.modules.employee.model.Server;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RoomCharge {
    @Id
    @GeneratedValue
    private int id;
    private LocalDate takenAt;
    @OneToOne(mappedBy = "roomCharge")
    private InvoiceItem invoiceItem;
}
