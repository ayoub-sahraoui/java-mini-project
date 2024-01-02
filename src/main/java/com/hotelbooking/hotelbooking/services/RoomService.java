package com.hotelbooking.hotelbooking.services;

import com.hotelbooking.hotelbooking.DTO.RoomDTO;
import com.hotelbooking.hotelbooking.exception.RoomNotFoundException;
import com.hotelbooking.hotelbooking.models.Room;
import com.hotelbooking.hotelbooking.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        return rooms.stream().map(RoomDTO::toDTO).collect(Collectors.toList());
    }


    public RoomDTO getRoomById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Room with ID " + id + " not found"));
        return RoomDTO.toDTO(room);
    }


    public RoomDTO createRoom(RoomDTO roomDTO) {
        Room room = new Room();
        mapRoomDTOToEntity(room, roomDTO);
        Room savedRoom = roomRepository.save(room);
        return RoomDTO.toDTO(savedRoom);
    }


    public RoomDTO updateRoom(Long id, RoomDTO roomDTO) {
        Room existingRoom = roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Room with ID " + id + " not found"));
        mapRoomDTOToEntity(existingRoom, roomDTO);
        Room updatedRoom = roomRepository.save(existingRoom);
        return RoomDTO.toDTO(updatedRoom);
    }

    public void deleteRoom(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Room with ID " + id + " not found"));
        roomRepository.delete(room);
    }

    private void mapRoomDTOToEntity(Room room, RoomDTO roomDTO) {
        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setStyle(roomDTO.getStyle());
        room.setStatus(roomDTO.getStatus());
        room.setBookingPrice(roomDTO.getBookingPrice());
        room.setSmoking(roomDTO.isSmoking());
    }
}
