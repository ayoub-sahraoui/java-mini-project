package com.hotelbooking.hotelbooking.modules.hotel.service;

import com.hotelbooking.hotelbooking.modules.hotel.model.HotelLocationDTO;
import com.hotelbooking.hotelbooking.modules.hotel.model.CreateHotelLocationDTO;
import com.hotelbooking.hotelbooking.modules.hotel.model.UpdateHotelLocationDTO;

import com.hotelbooking.hotelbooking.modules.hotel.model.HotelLocation;
import com.hotelbooking.hotelbooking.modules.hotel.model.EntityDTOConverter;
import com.hotelbooking.hotelbooking.modules.hotel.repository.HotelLocationRepository;
import com.hotelbooking.hotelbooking.modules.hotel.exception.HotelLocationNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotelLocationService {

    private final HotelLocationRepository hotelLocationRepository;

    @Autowired
    public HotelLocationService(HotelLocationRepository hotelLocationRepository) {
        this.hotelLocationRepository = hotelLocationRepository;
    }

    @Transactional(readOnly = true)
    public List<HotelLocationDTO> getAllHotelLocations() {
        List<HotelLocation> hotelLocations = hotelLocationRepository.findAll();
        return hotelLocations.stream()
                .map(hotelLocation -> EntityDTOConverter.convertToDTO(hotelLocation, HotelLocationDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public HotelLocationDTO getHotelLocationById(Long hotelLocationId) {
        Optional<HotelLocation> optionalHotelLocation = hotelLocationRepository.findById(hotelLocationId);
        if (optionalHotelLocation.isPresent()) {
            return EntityDTOConverter.convertToDTO(optionalHotelLocation.get(), HotelLocationDTO.class);
        } else {
            throw new HotelLocationNotFoundException("HotelLocation not found with ID: " + hotelLocationId);
        }
    }

    @Transactional
    public HotelLocationDTO createHotelLocation(CreateHotelLocationDTO hotelLocationDTO) {
        HotelLocation newHotelLocation = EntityDTOConverter.convertToEntity(hotelLocationDTO, HotelLocation.class);
        HotelLocation savedHotelLocation = hotelLocationRepository.save(newHotelLocation);
        return EntityDTOConverter.convertToDTO(savedHotelLocation, HotelLocationDTO.class);
    }

    @Transactional
    public HotelLocationDTO updateHotelLocation(Long hotelLocationId, UpdateHotelLocationDTO hotelLocationDTO) {
        Optional<HotelLocation> optionalHotelLocation = hotelLocationRepository.findById(hotelLocationId);
        if (optionalHotelLocation.isPresent()) {
            HotelLocation existingHotelLocation = optionalHotelLocation.get();
            BeanUtils.copyProperties(hotelLocationDTO, existingHotelLocation, "id");
            HotelLocation updatedHotelLocation = hotelLocationRepository.save(existingHotelLocation);
            return EntityDTOConverter.convertToDTO(updatedHotelLocation, HotelLocationDTO.class);
        }
        else{
            throw new HotelLocationNotFoundException("HotelLocation not found with ID: " + hotelLocationId);
        }
    }

    @Transactional
    public void deleteHotelLocation(Long hotelLocationId) {
        if (hotelLocationRepository.existsById(hotelLocationId)) {
            hotelLocationRepository.deleteById(hotelLocationId);
        }
        else{
            throw new HotelLocationNotFoundException("HotelLocation not found with ID: " + hotelLocationId);
        }
    }
}
