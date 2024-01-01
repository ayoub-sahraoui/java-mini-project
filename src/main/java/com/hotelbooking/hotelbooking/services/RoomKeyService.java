package com.hotelbooking.hotelbooking.services;

import com.hotelbooking.hotelbooking.DTO.RoomKeyDTO;
import com.hotelbooking.hotelbooking.models.RoomKey;
import com.hotelbooking.hotelbooking.repositories.RoomKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<RoomKeyDTO> getAllRoomKeys() {
        List<RoomKey> roomKeys = roomKeyRepository.findAll();
        return roomKeys.stream().map(RoomKeyDTO::toDTO).collect(Collectors.toList());
    }

    public RoomKeyDTO getRoomKeyById(int id) {
        Optional<RoomKey> roomKeyOptional = roomKeyRepository.findById(id);
        if (roomKeyOptional.isPresent()) {
            RoomKey roomKey = roomKeyOptional.get();
            return RoomKeyDTO.toDTO(roomKey);
        } else {
            throw new RuntimeException("Room key not found with ID: " + id);
        }
    }


    public RoomKeyDTO createRoomKey(RoomKeyDTO roomKeyDTO) {
        RoomKey roomKey = new RoomKey();

        roomKey.setBarcode(roomKeyDTO.getBarcode());
        roomKey.setIssuedAt(roomKeyDTO.getIssuedAt());
        roomKey.setActive(roomKeyDTO.isActive());
        roomKey.setMaster(roomKeyDTO.isMaster());

        RoomKey savedRoomKey = roomKeyRepository.save(roomKey);
        return RoomKeyDTO.toDTO(savedRoomKey);
    }

    public RoomKeyDTO updateRoomKey(int id, RoomKeyDTO roomKeyDTO) {
        Optional<RoomKey> roomKeyOptional = roomKeyRepository.findById(id);
        if (roomKeyOptional.isPresent()) {
            RoomKey existingRoomKey = roomKeyOptional.get();

            existingRoomKey.setBarcode(roomKeyDTO.getBarcode());
            existingRoomKey.setIssuedAt(roomKeyDTO.getIssuedAt());
            existingRoomKey.setActive(roomKeyDTO.isActive());
            existingRoomKey.setMaster(roomKeyDTO.isMaster());

            RoomKey updatedRoomKey = roomKeyRepository.save(existingRoomKey);
            return RoomKeyDTO.toDTO(updatedRoomKey);
        } else {

            throw new RuntimeException("Room key not found with ID: " + id);
        }
    }

    public void deleteRoomKey(int id) {
        Optional<RoomKey> roomKeyOptional = roomKeyRepository.findById(id);
        if (roomKeyOptional.isPresent()) {
            RoomKey roomKey = roomKeyOptional.get();
            roomKeyRepository.delete(roomKey);
        } else {

            throw new RuntimeException("Room key not found with ID: " + id);
        }
    }
}
