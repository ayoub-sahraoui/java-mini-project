package com.hotelbooking.hotelbooking.modules.invoice.controller;

import com.hotelbooking.hotelbooking.modules.invoice.model.InvoiceItemDTO;
import com.hotelbooking.hotelbooking.modules.invoice.model.CreateInvoiceItemDTO;
import com.hotelbooking.hotelbooking.modules.invoice.model.UpdateInvoiceItemDTO;
import com.hotelbooking.hotelbooking.modules.invoice.model.APIResponse;
import com.hotelbooking.hotelbooking.modules.invoice.service.InvoiceItemService;
import com.hotelbooking.hotelbooking.modules.invoice.exception.InvoiceItemNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/invoiceItems")
public class InvoiceItemController extends ResponseEntityExceptionHandler {

    @Autowired
    private InvoiceItemService invoiceItemService;

    // Get all invoiceItems
    @GetMapping
    public ResponseEntity<List<InvoiceItemDTO>> getAllInvoiceItems() {
        List<InvoiceItemDTO> invoiceItems = invoiceItemService.getAllInvoiceItems();
        return new ResponseEntity<>(invoiceItems, HttpStatus.OK);
    }

    // Get a specific invoiceItem by ID
    @GetMapping("/{invoiceItemId}")
    public ResponseEntity<Object> getInvoiceItemById(@PathVariable Long invoiceItemId) {
        try {
            InvoiceItemDTO invoiceItem = invoiceItemService.getInvoiceItemById(invoiceItemId);
            return new ResponseEntity<>(new APIResponse("success", "InvoiceItem retrieved successfully", invoiceItem), HttpStatus.OK);
        } catch (InvoiceItemNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Create a new invoiceItem
    @PostMapping
    public ResponseEntity<Object> createInvoiceItem(@RequestBody CreateInvoiceItemDTO invoiceItem) {
        try {
            InvoiceItemDTO createdInvoiceItem = invoiceItemService.createInvoiceItem(invoiceItem);
            return new ResponseEntity<>(new APIResponse("success", "InvoiceItem created successfully", createdInvoiceItem), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Update an existing invoiceItem
    @PutMapping("/{invoiceItemId}")
    public ResponseEntity<Object> updateInvoiceItem(@PathVariable Long invoiceItemId, @RequestBody UpdateInvoiceItemDTO invoiceItem) {
        try {
            InvoiceItemDTO updatedInvoiceItem = invoiceItemService.updateInvoiceItem(invoiceItemId, invoiceItem);
            return new ResponseEntity<>(new APIResponse("success", "InvoiceItem updated successfully", updatedInvoiceItem), HttpStatus.OK);
        } catch (InvoiceItemNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Delete a invoiceItem by ID
    @DeleteMapping("/{invoiceItemId}")
    public ResponseEntity<Object> deleteInvoiceItem(@PathVariable Long invoiceItemId) {
        try {
            invoiceItemService.deleteInvoiceItem(invoiceItemId);
            return new ResponseEntity<>(new APIResponse("success", "InvoiceItem deleted successfully", null), HttpStatus.NO_CONTENT);
        } catch (InvoiceItemNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }
}
