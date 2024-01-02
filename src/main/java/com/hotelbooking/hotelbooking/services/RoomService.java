package com.hotelbooking.hotelbooking.services;

import com.hotelbooking.hotelbooking.DTO.RoomDTO;
import com.hotelbooking.hotelbooking.DTO.RoomKeyDTO;
import com.hotelbooking.hotelbooking.exception.RoomNotFoundException;
import com.hotelbooking.hotelbooking.models.Room;
import com.hotelbooking.hotelbooking.models.RoomKey;
import com.hotelbooking.hotelbooking.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<RoomDTO> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream().map(this::convertRoomToDTO).collect(Collectors.toList());
    }

    public RoomDTO getRoomById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Room with ID " + id + " not found"));
        return convertRoomToDTO(room);
    }

    public RoomDTO createRoom(RoomDTO roomDTO) {
        Room room = convertDTOToRoom(roomDTO);
        Room savedRoom = roomRepository.save(room);
        return convertRoomToDTO(savedRoom);
    }

    public RoomDTO updateRoom(Long id, RoomDTO roomDTO) {
        Room existingRoom = roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Room with ID " + id + " not found"));
        mapDTOToRoom(existingRoom, roomDTO);
        Room updatedRoom = roomRepository.save(existingRoom);
        return convertRoomToDTO(updatedRoom);
    }

    public void deleteRoom(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Room with ID " + id + " not found"));
        roomRepository.delete(room);
    }

    private RoomDTO convertRoomToDTO(Room room) {
        return RoomDTO.builder()
                .id(room.getId())
                .roomNumber(room.getRoomNumber())
                .style(room.getStyle())
                .status(room.getStatus())
                .bookingPrice(room.getBookingPrice())
                .isSmoking(room.isSmoking())
                .roomKeys(convertRoomKeysToDTO(room.getRoomKeys()))
                .build();
    }

    private Set<RoomKeyDTO> convertRoomKeysToDTO(Set<RoomKey> roomKeys) {
        return roomKeys.stream()
                .map(this::convertRoomKeyToDTO)
                .collect(Collectors.toSet());
    }

    private RoomKeyDTO convertRoomKeyToDTO(RoomKey roomKey) {
        return RoomKeyDTO.builder()
                .keyId(roomKey.getKeyId())
                .barcode(roomKey.getBarcode())
                .issuedAt(roomKey.getIssuedAt())
                .active(roomKey.isActive())
                .isMaster(roomKey.isMaster())
                .build();
    }

    private Room convertDTOToRoom(RoomDTO roomDTO) {
        Room room = new Room();
        mapDTOToRoom(room, roomDTO);
        return room;
    }

    private void mapDTOToRoom(Room room, RoomDTO roomDTO) {
        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setStyle(roomDTO.getStyle());
        room.setStatus(roomDTO.getStatus());
        room.setBookingPrice(roomDTO.getBookingPrice());
        room.setSmoking(roomDTO.isSmoking());

        // Convert List<RoomKey> to Set<RoomKey>
        Set<RoomKey> roomKeys = roomDTO.getRoomKeys().stream()
                .map(this::convertRoomKeyDTOToEntity)
                .collect(Collectors.toSet());

        room.setRoomKeys(roomKeys);
    }


    private RoomKey convertRoomKeyDTOToEntity(RoomKeyDTO roomKeyDTO) {
        RoomKey roomKey = new RoomKey();
        roomKey.setKeyId(roomKeyDTO.getKeyId());
        roomKey.setBarcode(roomKeyDTO.getBarcode());
        roomKey.setIssuedAt(roomKeyDTO.getIssuedAt());
        roomKey.setActive(roomKeyDTO.isActive());
        roomKey.setMaster(roomKeyDTO.isMaster());
        // Assuming no Room association is set from RoomKeyDTO in this case, as it's unidirectional
        return roomKey;
    }
}
