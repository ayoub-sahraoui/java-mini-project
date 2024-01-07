package com.hotelbooking.hotelbooking.modules.room.service;

import com.hotelbooking.hotelbooking.modules.room.model.RoomHouseKeepingDTO;
import com.hotelbooking.hotelbooking.modules.room.model.CreateRoomHouseKeepingDTO;
import com.hotelbooking.hotelbooking.modules.room.model.UpdateRoomHouseKeepingDTO;

import com.hotelbooking.hotelbooking.modules.room.model.RoomHouseKeeping;
import com.hotelbooking.hotelbooking.modules.room.model.EntityDTOConverter;
import com.hotelbooking.hotelbooking.modules.room.repository.RoomHouseKeepingRepository;
import com.hotelbooking.hotelbooking.modules.room.exception.RoomHouseKeepingNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomHouseKeepingService {

    private final RoomHouseKeepingRepository roomHouseKeepingRepository;

    @Autowired
    public RoomHouseKeepingService(RoomHouseKeepingRepository roomHouseKeepingRepository) {
        this.roomHouseKeepingRepository = roomHouseKeepingRepository;
    }

    @Transactional(readOnly = true)
    public List<RoomHouseKeepingDTO> getAllRoomHouseKeepings() {
        List<RoomHouseKeeping> roomHouseKeepings = roomHouseKeepingRepository.findAll();
        return roomHouseKeepings.stream()
                .map(roomHouseKeeping -> EntityDTOConverter.convertToDTO(roomHouseKeeping, RoomHouseKeepingDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RoomHouseKeepingDTO getRoomHouseKeepingById(Long roomHouseKeepingId) {
        Optional<RoomHouseKeeping> optionalRoomHouseKeeping = roomHouseKeepingRepository.findById(roomHouseKeepingId);
        if (optionalRoomHouseKeeping.isPresent()) {
            return EntityDTOConverter.convertToDTO(optionalRoomHouseKeeping.get(), RoomHouseKeepingDTO.class);
        } else {
            throw new RoomHouseKeepingNotFoundException("RoomHouseKeeping not found with ID: " + roomHouseKeepingId);
        }
    }

    @Transactional
    public RoomHouseKeepingDTO createRoomHouseKeeping(CreateRoomHouseKeepingDTO roomHouseKeepingDTO) {
        RoomHouseKeeping newRoomHouseKeeping = EntityDTOConverter.convertToEntity(roomHouseKeepingDTO, RoomHouseKeeping.class);
        RoomHouseKeeping savedRoomHouseKeeping = roomHouseKeepingRepository.save(newRoomHouseKeeping);
        return EntityDTOConverter.convertToDTO(savedRoomHouseKeeping, RoomHouseKeepingDTO.class);
    }

    @Transactional
    public RoomHouseKeepingDTO updateRoomHouseKeeping(Long roomHouseKeepingId, UpdateRoomHouseKeepingDTO roomHouseKeepingDTO) {
        Optional<RoomHouseKeeping> optionalRoomHouseKeeping = roomHouseKeepingRepository.findById(roomHouseKeepingId);
        if (optionalRoomHouseKeeping.isPresent()) {
            RoomHouseKeeping existingRoomHouseKeeping = optionalRoomHouseKeeping.get();
            BeanUtils.copyProperties(roomHouseKeepingDTO, existingRoomHouseKeeping, "id");
            RoomHouseKeeping updatedRoomHouseKeeping = roomHouseKeepingRepository.save(existingRoomHouseKeeping);
            return EntityDTOConverter.convertToDTO(updatedRoomHouseKeeping, RoomHouseKeepingDTO.class);
        }
        else{
            throw new RoomHouseKeepingNotFoundException("RoomHouseKeeping not found with ID: " + roomHouseKeepingId);
        }
    }

    @Transactional
    public void deleteRoomHouseKeeping(Long roomHouseKeepingId) {
        if (roomHouseKeepingRepository.existsById(roomHouseKeepingId)) {
            roomHouseKeepingRepository.deleteById(roomHouseKeepingId);
        }
        else{
            throw new RoomHouseKeepingNotFoundException("RoomHouseKeeping not found with ID: " + roomHouseKeepingId);
        }
    }
}
