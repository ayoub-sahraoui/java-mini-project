package com.hotelbooking.hotelbooking.modules.invoice.service;

import com.hotelbooking.hotelbooking.modules.invoice.model.InvoiceDTO;
import com.hotelbooking.hotelbooking.modules.invoice.model.CreateInvoiceDTO;
import com.hotelbooking.hotelbooking.modules.invoice.model.UpdateInvoiceDTO;

import com.hotelbooking.hotelbooking.modules.invoice.model.Invoice;
import com.hotelbooking.hotelbooking.modules.invoice.model.EntityDTOConverter;
import com.hotelbooking.hotelbooking.modules.invoice.repository.InvoiceRepository;
import com.hotelbooking.hotelbooking.modules.invoice.exception.InvoiceNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Transactional(readOnly = true)
    public List<InvoiceDTO> getAllInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        return invoices.stream()
                .map(invoice -> EntityDTOConverter.convertToDTO(invoice, InvoiceDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public InvoiceDTO getInvoiceById(Long invoiceId) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(invoiceId);
        if (optionalInvoice.isPresent()) {
            return EntityDTOConverter.convertToDTO(optionalInvoice.get(), InvoiceDTO.class);
        } else {
            throw new InvoiceNotFoundException("Invoice not found with ID: " + invoiceId);
        }
    }

    @Transactional
    public InvoiceDTO createInvoice(CreateInvoiceDTO invoiceDTO) {
        Invoice newInvoice = EntityDTOConverter.convertToEntity(invoiceDTO, Invoice.class);
        Invoice savedInvoice = invoiceRepository.save(newInvoice);
        return EntityDTOConverter.convertToDTO(savedInvoice, InvoiceDTO.class);
    }

    @Transactional
    public InvoiceDTO updateInvoice(Long invoiceId, UpdateInvoiceDTO invoiceDTO) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(invoiceId);
        if (optionalInvoice.isPresent()) {
            Invoice existingInvoice = optionalInvoice.get();
            BeanUtils.copyProperties(invoiceDTO, existingInvoice, "id");
            Invoice updatedInvoice = invoiceRepository.save(existingInvoice);
            return EntityDTOConverter.convertToDTO(updatedInvoice, InvoiceDTO.class);
        }
        else{
            throw new InvoiceNotFoundException("Invoice not found with ID: " + invoiceId);
        }
    }

    @Transactional
    public void deleteInvoice(Long invoiceId) {
        if (invoiceRepository.existsById(invoiceId)) {
            invoiceRepository.deleteById(invoiceId);
        }
        else{
            throw new InvoiceNotFoundException("Invoice not found with ID: " + invoiceId);
        }
    }
}
