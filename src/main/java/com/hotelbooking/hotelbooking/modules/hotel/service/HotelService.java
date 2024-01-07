package com.hotelbooking.hotelbooking.modules.hotel.service;

import com.hotelbooking.hotelbooking.modules.hotel.model.*;

import com.hotelbooking.hotelbooking.modules.hotel.repository.HotelRepository;
import com.hotelbooking.hotelbooking.modules.hotel.exception.HotelNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Transactional(readOnly = true)
    public List<HotelDTO> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels.stream()
                .map(hotel -> EntityDTOConverter.convertToDTO(hotel, HotelDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public HotelDTO getHotelById(Long hotelId) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(hotelId);
        if (optionalHotel.isPresent()) {
            return EntityDTOConverter.convertToDTO(optionalHotel.get(), HotelDTO.class);
        } else {
            throw new HotelNotFoundException("Hotel not found with ID: " + hotelId);
        }
    }

    @Transactional
    public HotelDTO createHotel(CreateHotelDTO hotelDTO) {
        Hotel newHotel = EntityDTOConverter.convertToEntity(hotelDTO, Hotel.class);
        Hotel savedHotel = hotelRepository.save(newHotel);
        return EntityDTOConverter.convertToDTO(savedHotel, HotelDTO.class);
    }

    @Transactional
    public HotelDTO updateHotel(Long hotelId, UpdateHotelDTO hotelDTO) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(hotelId);
        if (optionalHotel.isPresent()) {
            Hotel existingHotel = optionalHotel.get();
            BeanUtils.copyProperties(hotelDTO, existingHotel, "id");
            Hotel updatedHotel = hotelRepository.save(existingHotel);
            return EntityDTOConverter.convertToDTO(updatedHotel, HotelDTO.class);
        }
        else{
            throw new HotelNotFoundException("Hotel not found with ID: " + hotelId);
        }
    }

    @Transactional
    public void deleteHotel(Long hotelId) {
        if (hotelRepository.existsById(hotelId)) {
            hotelRepository.deleteById(hotelId);
        }
        else{
            throw new HotelNotFoundException("Hotel not found with ID: " + hotelId);
        }
    }

    @Transactional(readOnly = true)
    public List<HotelLocationDTO> getAllHotelLocations(Long hotelId) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(hotelId);
        if (optionalHotel.isPresent()) {
            List<HotelLocation> hotelLocations = optionalHotel.get().getLocations();
            return hotelLocations.stream()
                    .map(hotelLocation -> EntityDTOConverter.convertToDTO(hotelLocation, HotelLocationDTO.class))
                    .collect(Collectors.toList());
        } else {
            throw new HotelNotFoundException("Hotel not found with ID: " + hotelId);
        }
    }
}
