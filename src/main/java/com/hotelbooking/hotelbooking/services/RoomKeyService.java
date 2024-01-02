package com.hotelbooking.hotelbooking.services;

import com.hotelbooking.hotelbooking.DTO.RoomKeyDTO;
import com.hotelbooking.hotelbooking.exception.RoomKeyNotFoundException;
import com.hotelbooking.hotelbooking.models.RoomKey;
import com.hotelbooking.hotelbooking.repositories.RoomKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        return roomKeyRepository.findById(id)
                .map(RoomKeyDTO::toDTO)
                .orElseThrow(() -> new RoomKeyNotFoundException("Room key not found with ID: " + id));
    }

    public RoomKeyDTO createRoomKey(RoomKeyDTO roomKeyDTO) {
        RoomKey roomKey = convertDTOToRoomKey(roomKeyDTO);
        RoomKey savedRoomKey = roomKeyRepository.save(roomKey);
        return RoomKeyDTO.toDTO(savedRoomKey);
    }

    public RoomKeyDTO updateRoomKey(int id, RoomKeyDTO roomKeyDTO) {
        return roomKeyRepository.findById(id)
                .map(existingRoomKey -> {
                    updateRoomKeyFromDTO(existingRoomKey, roomKeyDTO);
                    RoomKey updatedRoomKey = roomKeyRepository.save(existingRoomKey);
                    return RoomKeyDTO.toDTO(updatedRoomKey);
                })
                .orElseThrow(() -> new RoomKeyNotFoundException("Room key not found with ID: " + id));
    }

    public void deleteRoomKey(int id) {
        RoomKey roomKey = roomKeyRepository.findById(id)
                .orElseThrow(() -> new RoomKeyNotFoundException("Room key not found with ID: " + id));
        roomKeyRepository.delete(roomKey);
    }

    private RoomKey convertDTOToRoomKey(RoomKeyDTO roomKeyDTO) {
        RoomKey roomKey = new RoomKey();
        updateRoomKeyFromDTO(roomKey, roomKeyDTO);
        return roomKey;
    }

    private void updateRoomKeyFromDTO(RoomKey roomKey, RoomKeyDTO roomKeyDTO) {
        roomKey.setBarcode(roomKeyDTO.getBarcode());
        roomKey.setIssuedAt(roomKeyDTO.getIssuedAt());
        roomKey.setActive(roomKeyDTO.isActive());
        roomKey.setMaster(roomKeyDTO.isMaster());
    }
}
