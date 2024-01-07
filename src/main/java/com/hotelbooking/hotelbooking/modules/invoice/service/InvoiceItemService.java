package com.hotelbooking.hotelbooking.modules.invoice.service;

import com.hotelbooking.hotelbooking.modules.invoice.model.InvoiceItemDTO;
import com.hotelbooking.hotelbooking.modules.invoice.model.CreateInvoiceItemDTO;
import com.hotelbooking.hotelbooking.modules.invoice.model.UpdateInvoiceItemDTO;

import com.hotelbooking.hotelbooking.modules.invoice.model.InvoiceItem;
import com.hotelbooking.hotelbooking.modules.invoice.model.EntityDTOConverter;
import com.hotelbooking.hotelbooking.modules.invoice.repository.InvoiceItemRepository;
import com.hotelbooking.hotelbooking.modules.invoice.exception.InvoiceItemNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceItemService {

    private final InvoiceItemRepository invoiceItemRepository;

    @Autowired
    public InvoiceItemService(InvoiceItemRepository invoiceItemRepository) {
        this.invoiceItemRepository = invoiceItemRepository;
    }

    @Transactional(readOnly = true)
    public List<InvoiceItemDTO> getAllInvoiceItems() {
        List<InvoiceItem> invoiceItems = invoiceItemRepository.findAll();
        return invoiceItems.stream()
                .map(invoiceItem -> EntityDTOConverter.convertToDTO(invoiceItem, InvoiceItemDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public InvoiceItemDTO getInvoiceItemById(Long invoiceItemId) {
        Optional<InvoiceItem> optionalInvoiceItem = invoiceItemRepository.findById(invoiceItemId);
        if (optionalInvoiceItem.isPresent()) {
            return EntityDTOConverter.convertToDTO(optionalInvoiceItem.get(), InvoiceItemDTO.class);
        } else {
            throw new InvoiceItemNotFoundException("InvoiceItem not found with ID: " + invoiceItemId);
        }
    }

    @Transactional
    public InvoiceItemDTO createInvoiceItem(CreateInvoiceItemDTO invoiceItemDTO) {
        InvoiceItem newInvoiceItem = EntityDTOConverter.convertToEntity(invoiceItemDTO, InvoiceItem.class);
        InvoiceItem savedInvoiceItem = invoiceItemRepository.save(newInvoiceItem);
        return EntityDTOConverter.convertToDTO(savedInvoiceItem, InvoiceItemDTO.class);
    }

    @Transactional
    public InvoiceItemDTO updateInvoiceItem(Long invoiceItemId, UpdateInvoiceItemDTO invoiceItemDTO) {
        Optional<InvoiceItem> optionalInvoiceItem = invoiceItemRepository.findById(invoiceItemId);
        if (optionalInvoiceItem.isPresent()) {
            InvoiceItem existingInvoiceItem = optionalInvoiceItem.get();
            BeanUtils.copyProperties(invoiceItemDTO, existingInvoiceItem, "id");
            InvoiceItem updatedInvoiceItem = invoiceItemRepository.save(existingInvoiceItem);
            return EntityDTOConverter.convertToDTO(updatedInvoiceItem, InvoiceItemDTO.class);
        }
        else{
            throw new InvoiceItemNotFoundException("InvoiceItem not found with ID: " + invoiceItemId);
        }
    }

    @Transactional
    public void deleteInvoiceItem(Long invoiceItemId) {
        if (invoiceItemRepository.existsById(invoiceItemId)) {
            invoiceItemRepository.deleteById(invoiceItemId);
        }
        else{
            throw new InvoiceItemNotFoundException("InvoiceItem not found with ID: " + invoiceItemId);
        }
    }
}
