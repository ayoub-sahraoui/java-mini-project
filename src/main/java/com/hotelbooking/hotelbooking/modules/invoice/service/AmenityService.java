package com.hotelbooking.hotelbooking.modules.invoice.service;

import com.hotelbooking.hotelbooking.modules.invoice.model.AmenityDTO;
import com.hotelbooking.hotelbooking.modules.invoice.model.CreateAmenityDTO;
import com.hotelbooking.hotelbooking.modules.invoice.model.UpdateAmenityDTO;

import com.hotelbooking.hotelbooking.modules.invoice.model.Amenity;
import com.hotelbooking.hotelbooking.modules.invoice.model.EntityDTOConverter;
import com.hotelbooking.hotelbooking.modules.invoice.repository.AmenityRepository;
import com.hotelbooking.hotelbooking.modules.invoice.exception.AmenityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AmenityService {

    private final AmenityRepository amenityRepository;

    @Autowired
    public AmenityService(AmenityRepository amenityRepository) {
        this.amenityRepository = amenityRepository;
    }

    @Transactional(readOnly = true)
    public List<AmenityDTO> getAllAmenities() {
        List<Amenity> amenities = amenityRepository.findAll();
        return amenities.stream()
                .map(amenity -> EntityDTOConverter.convertToDTO(amenity, AmenityDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AmenityDTO getAmenityById(Long amenityId) {
        Optional<Amenity> optionalAmenity = amenityRepository.findById(amenityId);
        if (optionalAmenity.isPresent()) {
            return EntityDTOConverter.convertToDTO(optionalAmenity.get(), AmenityDTO.class);
        } else {
            throw new AmenityNotFoundException("Amenity not found with ID: " + amenityId);
        }
    }

    @Transactional
    public AmenityDTO createAmenity(CreateAmenityDTO amenityDTO) {
        Amenity newAmenity = EntityDTOConverter.convertToEntity(amenityDTO, Amenity.class);
        Amenity savedAmenity = amenityRepository.save(newAmenity);
        return EntityDTOConverter.convertToDTO(savedAmenity, AmenityDTO.class);
    }

    @Transactional
    public AmenityDTO updateAmenity(Long amenityId, UpdateAmenityDTO amenityDTO) {
        Optional<Amenity> optionalAmenity = amenityRepository.findById(amenityId);
        if (optionalAmenity.isPresent()) {
            Amenity existingAmenity = optionalAmenity.get();
            BeanUtils.copyProperties(amenityDTO, existingAmenity, "id");
            Amenity updatedAmenity = amenityRepository.save(existingAmenity);
            return EntityDTOConverter.convertToDTO(updatedAmenity, AmenityDTO.class);
        }
        else{
            throw new AmenityNotFoundException("Amenity not found with ID: " + amenityId);
        }
    }

    @Transactional
    public void deleteAmenity(Long amenityId) {
        if (amenityRepository.existsById(amenityId)) {
            amenityRepository.deleteById(amenityId);
        }
        else{
            throw new AmenityNotFoundException("Amenity not found with ID: " + amenityId);
        }
    }
}
