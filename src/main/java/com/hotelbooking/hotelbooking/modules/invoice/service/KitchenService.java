package com.hotelbooking.hotelbooking.modules.invoice.service;

import com.hotelbooking.hotelbooking.modules.invoice.model.KitchenDTO;
import com.hotelbooking.hotelbooking.modules.invoice.model.CreateKitchenDTO;
import com.hotelbooking.hotelbooking.modules.invoice.model.UpdateKitchenDTO;

import com.hotelbooking.hotelbooking.modules.invoice.model.Kitchen;
import com.hotelbooking.hotelbooking.modules.invoice.model.EntityDTOConverter;
import com.hotelbooking.hotelbooking.modules.invoice.repository.KitchenRepository;
import com.hotelbooking.hotelbooking.modules.invoice.exception.KitchenNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class KitchenService {

    private final KitchenRepository kitchenRepository;

    @Autowired
    public KitchenService(KitchenRepository kitchenRepository) {
        this.kitchenRepository = kitchenRepository;
    }

    @Transactional(readOnly = true)
    public List<KitchenDTO> getAllKitchens() {
        List<Kitchen> kitchens = kitchenRepository.findAll();
        return kitchens.stream()
                .map(kitchen -> EntityDTOConverter.convertToDTO(kitchen, KitchenDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public KitchenDTO getKitchenById(Long kitchenId) {
        Optional<Kitchen> optionalKitchen = kitchenRepository.findById(kitchenId);
        if (optionalKitchen.isPresent()) {
            return EntityDTOConverter.convertToDTO(optionalKitchen.get(), KitchenDTO.class);
        } else {
            throw new KitchenNotFoundException("Kitchen not found with ID: " + kitchenId);
        }
    }

    @Transactional
    public KitchenDTO createKitchen(CreateKitchenDTO kitchenDTO) {
        Kitchen newKitchen = EntityDTOConverter.convertToEntity(kitchenDTO, Kitchen.class);
        Kitchen savedKitchen = kitchenRepository.save(newKitchen);
        return EntityDTOConverter.convertToDTO(savedKitchen, KitchenDTO.class);
    }

    @Transactional
    public KitchenDTO updateKitchen(Long kitchenId, UpdateKitchenDTO kitchenDTO) {
        Optional<Kitchen> optionalKitchen = kitchenRepository.findById(kitchenId);
        if (optionalKitchen.isPresent()) {
            Kitchen existingKitchen = optionalKitchen.get();
            BeanUtils.copyProperties(kitchenDTO, existingKitchen, "id");
            Kitchen updatedKitchen = kitchenRepository.save(existingKitchen);
            return EntityDTOConverter.convertToDTO(updatedKitchen, KitchenDTO.class);
        }
        else{
            throw new KitchenNotFoundException("Kitchen not found with ID: " + kitchenId);
        }
    }

    @Transactional
    public void deleteKitchen(Long kitchenId) {
        if (kitchenRepository.existsById(kitchenId)) {
            kitchenRepository.deleteById(kitchenId);
        }
        else{
            throw new KitchenNotFoundException("Kitchen not found with ID: " + kitchenId);
        }
    }
}
