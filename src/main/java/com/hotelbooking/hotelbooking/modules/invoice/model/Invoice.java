package com.hotelbooking.hotelbooking.modules.invoice.model;

import com.hotelbooking.hotelbooking.modules.room.model.RoomBooking;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Invoice {
    @Id
    @GeneratedValue
    private int id;
    private double amount;
    @OneToMany(mappedBy = "invoice")
    private List<InvoiceItem> invoiceItems;
    @OneToOne(mappedBy = "invoice")
    private RoomBooking roomBooking;
}
