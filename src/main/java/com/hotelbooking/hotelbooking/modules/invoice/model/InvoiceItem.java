package com.hotelbooking.hotelbooking.modules.invoice.model;

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
public class InvoiceItem {
    @Id
    @GeneratedValue
    private int id;
    private double amount;
    @ManyToOne
    @JoinColumn(name="invoice_id")
    private Invoice invoice;
    @OneToOne
    @JoinColumn(name = "roomCharge_id")
    private RoomCharge roomCharge;
}
