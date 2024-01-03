package com.hotelbooking.hotelbooking.modules.Room_Key_Management.services;

import com.hotelbooking.hotelbooking.modules.Room_Key_Management.DTO.RoomKeyDTO;
import com.hotelbooking.hotelbooking.modules.Room_Key_Management.models.Room;
import com.hotelbooking.hotelbooking.modules.Room_Key_Management.models.RoomKey;
import com.hotelbooking.hotelbooking.modules.Room_Key_Management.repositories.RoomKeyRepository;
import com.hotelbooking.hotelbooking.modules.Room_Key_Management.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomKeyService {

    private final RoomKeyRepository roomKeyRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public RoomKeyService(RoomKeyRepository roomKeyRepository, RoomRepository roomRepository) {
        this.roomKeyRepository = roomKeyRepository;
        this.roomRepository = roomRepository;
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

    public RoomKeyDTO createRoomKey(RoomKeyDTO roomKeyDTO, Long roomId) {
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        if (optionalRoom.isPresent()) {
            RoomKey roomKey = new RoomKey();

            roomKey.setBarcode(roomKeyDTO.getBarcode());
            roomKey.setIssuedAt(roomKeyDTO.getIssuedAt());
            roomKey.setActive(roomKeyDTO.isActive());
            roomKey.setMaster(roomKeyDTO.isMaster());

            Room room = optionalRoom.get();
            roomKey.setRoom(room);

            RoomKey savedRoomKey = roomKeyRepository.save(roomKey);
            return RoomKeyDTO.toDTO(savedRoomKey);
        } else {
            throw new RuntimeException("Room not found with ID: " + roomId);
        }
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
