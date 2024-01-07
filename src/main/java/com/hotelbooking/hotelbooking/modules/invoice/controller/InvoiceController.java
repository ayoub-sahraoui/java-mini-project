package com.hotelbooking.hotelbooking.modules.invoice.controller;

import com.hotelbooking.hotelbooking.modules.invoice.model.InvoiceDTO;
import com.hotelbooking.hotelbooking.modules.invoice.model.CreateInvoiceDTO;
import com.hotelbooking.hotelbooking.modules.invoice.model.UpdateInvoiceDTO;
import com.hotelbooking.hotelbooking.modules.invoice.model.APIResponse;
import com.hotelbooking.hotelbooking.modules.invoice.service.InvoiceService;
import com.hotelbooking.hotelbooking.modules.invoice.exception.InvoiceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController extends ResponseEntityExceptionHandler {

    @Autowired
    private InvoiceService invoiceService;

    // Get all invoices
    @GetMapping
    public ResponseEntity<List<InvoiceDTO>> getAllInvoices() {
        List<InvoiceDTO> invoices = invoiceService.getAllInvoices();
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    // Get a specific invoice by ID
    @GetMapping("/{invoiceId}")
    public ResponseEntity<Object> getInvoiceById(@PathVariable Long invoiceId) {
        try {
            InvoiceDTO invoice = invoiceService.getInvoiceById(invoiceId);
            return new ResponseEntity<>(new APIResponse("success", "Invoice retrieved successfully", invoice), HttpStatus.OK);
        } catch (InvoiceNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Create a new invoice
    @PostMapping
    public ResponseEntity<Object> createInvoice(@RequestBody CreateInvoiceDTO invoice) {
        try {
            InvoiceDTO createdInvoice = invoiceService.createInvoice(invoice);
            return new ResponseEntity<>(new APIResponse("success", "Invoice created successfully", createdInvoice), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Update an existing invoice
    @PutMapping("/{invoiceId}")
    public ResponseEntity<Object> updateInvoice(@PathVariable Long invoiceId, @RequestBody UpdateInvoiceDTO invoice) {
        try {
            InvoiceDTO updatedInvoice = invoiceService.updateInvoice(invoiceId, invoice);
            return new ResponseEntity<>(new APIResponse("success", "Invoice updated successfully", updatedInvoice), HttpStatus.OK);
        } catch (InvoiceNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Delete a invoice by ID
    @DeleteMapping("/{invoiceId}")
    public ResponseEntity<Object> deleteInvoice(@PathVariable Long invoiceId) {
        try {
            invoiceService.deleteInvoice(invoiceId);
            return new ResponseEntity<>(new APIResponse("success", "Invoice deleted successfully", null), HttpStatus.NO_CONTENT);
        } catch (InvoiceNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }
}
