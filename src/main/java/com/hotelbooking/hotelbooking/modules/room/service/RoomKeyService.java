package com.hotelbooking.hotelbooking.modules.room.service;

import com.hotelbooking.hotelbooking.modules.room.model.RoomKeyDTO;
import com.hotelbooking.hotelbooking.modules.room.model.CreateRoomKeyDTO;
import com.hotelbooking.hotelbooking.modules.room.model.UpdateRoomKeyDTO;

import com.hotelbooking.hotelbooking.modules.room.model.RoomKey;
import com.hotelbooking.hotelbooking.modules.room.model.EntityDTOConverter;
import com.hotelbooking.hotelbooking.modules.room.repository.RoomKeyRepository;
import com.hotelbooking.hotelbooking.modules.room.exception.RoomKeyNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomKeyService {

    private final RoomKeyRepository roomKeyRepository;

    @Autowired
    public RoomKeyService(RoomKeyRepository roomKeyRepository) {
        this.roomKeyRepository = roomKeyRepository;
    }

    @Transactional(readOnly = true)
    public List<RoomKeyDTO> getAllRoomKeys() {
        List<RoomKey> roomKeys = roomKeyRepository.findAll();
        return roomKeys.stream()
                .map(roomKey -> EntityDTOConverter.convertToDTO(roomKey, RoomKeyDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RoomKeyDTO getRoomKeyById(Long roomKeyId) {
        Optional<RoomKey> optionalRoomKey = roomKeyRepository.findById(roomKeyId);
        if (optionalRoomKey.isPresent()) {
            return EntityDTOConverter.convertToDTO(optionalRoomKey.get(), RoomKeyDTO.class);
        } else {
            throw new RoomKeyNotFoundException("RoomKey not found with ID: " + roomKeyId);
        }
    }

    @Transactional
    public RoomKeyDTO createRoomKey(CreateRoomKeyDTO roomKeyDTO) {
        RoomKey newRoomKey = EntityDTOConverter.convertToEntity(roomKeyDTO, RoomKey.class);
        RoomKey savedRoomKey = roomKeyRepository.save(newRoomKey);
        return EntityDTOConverter.convertToDTO(savedRoomKey, RoomKeyDTO.class);
    }

    @Transactional
    public RoomKeyDTO updateRoomKey(Long roomKeyId, UpdateRoomKeyDTO roomKeyDTO) {
        Optional<RoomKey> optionalRoomKey = roomKeyRepository.findById(roomKeyId);
        if (optionalRoomKey.isPresent()) {
            RoomKey existingRoomKey = optionalRoomKey.get();
            BeanUtils.copyProperties(roomKeyDTO, existingRoomKey, "id");
            RoomKey updatedRoomKey = roomKeyRepository.save(existingRoomKey);
            return EntityDTOConverter.convertToDTO(updatedRoomKey, RoomKeyDTO.class);
        }
        else{
            throw new RoomKeyNotFoundException("RoomKey not found with ID: " + roomKeyId);
        }
    }

    @Transactional
    public void deleteRoomKey(Long roomKeyId) {
        if (roomKeyRepository.existsById(roomKeyId)) {
            roomKeyRepository.deleteById(roomKeyId);
        }
        else{
            throw new RoomKeyNotFoundException("RoomKey not found with ID: " + roomKeyId);
        }
    }
}
