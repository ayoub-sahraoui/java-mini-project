package com.hotelbooking.hotelbooking.modules.employee.service;

import com.hotelbooking.hotelbooking.modules.employee.model.GuestDTO;
import com.hotelbooking.hotelbooking.modules.employee.model.CreateGuestDTO;
import com.hotelbooking.hotelbooking.modules.employee.model.UpdateGuestDTO;

import com.hotelbooking.hotelbooking.modules.employee.model.Guest;
import com.hotelbooking.hotelbooking.modules.employee.model.EntityDTOConverter;
import com.hotelbooking.hotelbooking.modules.employee.repository.GuestRepository;
import com.hotelbooking.hotelbooking.modules.employee.exception.GuestNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GuestService {

    private final GuestRepository guestRepository;

    @Autowired
    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Transactional(readOnly = true)
    public List<GuestDTO> getAllGuests() {
        List<Guest> guests = guestRepository.findAll();
        return guests.stream()
                .map(guest -> EntityDTOConverter.convertToDTO(guest, GuestDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public GuestDTO getGuestById(Long guestId) {
        Optional<Guest> optionalGuest = guestRepository.findById(guestId);
        if (optionalGuest.isPresent()) {
            return EntityDTOConverter.convertToDTO(optionalGuest.get(), GuestDTO.class);
        } else {
            throw new GuestNotFoundException("Guest not found with ID: " + guestId);
        }
    }

    @Transactional
    public GuestDTO createGuest(CreateGuestDTO guestDTO) {
        Guest newGuest = EntityDTOConverter.convertToEntity(guestDTO, Guest.class);
        Guest savedGuest = guestRepository.save(newGuest);
        return EntityDTOConverter.convertToDTO(savedGuest, GuestDTO.class);
    }

    @Transactional
    public GuestDTO updateGuest(Long guestId, UpdateGuestDTO guestDTO) {
        Optional<Guest> optionalGuest = guestRepository.findById(guestId);
        if (optionalGuest.isPresent()) {
            Guest existingGuest = optionalGuest.get();
            BeanUtils.copyProperties(guestDTO, existingGuest, "id");
            Guest updatedGuest = guestRepository.save(existingGuest);
            return EntityDTOConverter.convertToDTO(updatedGuest, GuestDTO.class);
        }
        else{
            throw new GuestNotFoundException("Guest not found with ID: " + guestId);
        }
    }

    @Transactional
    public void deleteGuest(Long guestId) {
        if (guestRepository.existsById(guestId)) {
            guestRepository.deleteById(guestId);
        }
        else{
            throw new GuestNotFoundException("Guest not found with ID: " + guestId);
        }
    }
}
