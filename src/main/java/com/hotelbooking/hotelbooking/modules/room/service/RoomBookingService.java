package com.hotelbooking.hotelbooking.modules.room.service;

import com.hotelbooking.hotelbooking.modules.room.model.RoomBookingDTO;
import com.hotelbooking.hotelbooking.modules.room.model.CreateRoomBookingDTO;
import com.hotelbooking.hotelbooking.modules.room.model.UpdateRoomBookingDTO;

import com.hotelbooking.hotelbooking.modules.room.model.RoomBooking;
import com.hotelbooking.hotelbooking.modules.room.model.EntityDTOConverter;
import com.hotelbooking.hotelbooking.modules.room.repository.RoomBookingRepository;
import com.hotelbooking.hotelbooking.modules.room.exception.RoomBookingNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomBookingService {

    private final RoomBookingRepository roomBookingRepository;

    @Autowired
    public RoomBookingService(RoomBookingRepository roomBookingRepository) {
        this.roomBookingRepository = roomBookingRepository;
    }

    @Transactional(readOnly = true)
    public List<RoomBookingDTO> getAllRoomBookings() {
        List<RoomBooking> roomBookings = roomBookingRepository.findAll();
        return roomBookings.stream()
                .map(roomBooking -> EntityDTOConverter.convertToDTO(roomBooking, RoomBookingDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RoomBookingDTO getRoomBookingById(Long roomBookingId) {
        Optional<RoomBooking> optionalRoomBooking = roomBookingRepository.findById(roomBookingId);
        if (optionalRoomBooking.isPresent()) {
            return EntityDTOConverter.convertToDTO(optionalRoomBooking.get(), RoomBookingDTO.class);
        } else {
            throw new RoomBookingNotFoundException("RoomBooking not found with ID: " + roomBookingId);
        }
    }

    @Transactional
    public RoomBookingDTO createRoomBooking(CreateRoomBookingDTO roomBookingDTO) {
        RoomBooking newRoomBooking = EntityDTOConverter.convertToEntity(roomBookingDTO, RoomBooking.class);
        RoomBooking savedRoomBooking = roomBookingRepository.save(newRoomBooking);
        return EntityDTOConverter.convertToDTO(savedRoomBooking, RoomBookingDTO.class);
    }

    @Transactional
    public RoomBookingDTO updateRoomBooking(Long roomBookingId, UpdateRoomBookingDTO roomBookingDTO) {
        Optional<RoomBooking> optionalRoomBooking = roomBookingRepository.findById(roomBookingId);
        if (optionalRoomBooking.isPresent()) {
            RoomBooking existingRoomBooking = optionalRoomBooking.get();
            BeanUtils.copyProperties(roomBookingDTO, existingRoomBooking, "id");
            RoomBooking updatedRoomBooking = roomBookingRepository.save(existingRoomBooking);
            return EntityDTOConverter.convertToDTO(updatedRoomBooking, RoomBookingDTO.class);
        }
        else{
            throw new RoomBookingNotFoundException("RoomBooking not found with ID: " + roomBookingId);
        }
    }

    @Transactional
    public void deleteRoomBooking(Long roomBookingId) {
        if (roomBookingRepository.existsById(roomBookingId)) {
            roomBookingRepository.deleteById(roomBookingId);
        }
        else{
            throw new RoomBookingNotFoundException("RoomBooking not found with ID: " + roomBookingId);
        }
    }
}
