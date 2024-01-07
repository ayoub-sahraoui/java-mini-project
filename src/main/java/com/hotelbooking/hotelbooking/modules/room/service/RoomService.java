package com.hotelbooking.hotelbooking.modules.room.service;

import com.hotelbooking.hotelbooking.modules.room.model.RoomDTO;
import com.hotelbooking.hotelbooking.modules.room.model.CreateRoomDTO;
import com.hotelbooking.hotelbooking.modules.room.model.UpdateRoomDTO;

import com.hotelbooking.hotelbooking.modules.room.model.Room;
import com.hotelbooking.hotelbooking.modules.room.model.EntityDTOConverter;
import com.hotelbooking.hotelbooking.modules.room.repository.RoomRepository;
import com.hotelbooking.hotelbooking.modules.room.exception.RoomNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Transactional(readOnly = true)
    public List<RoomDTO> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream()
                .map(room -> EntityDTOConverter.convertToDTO(room, RoomDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RoomDTO getRoomById(Long roomId) {
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        if (optionalRoom.isPresent()) {
            return EntityDTOConverter.convertToDTO(optionalRoom.get(), RoomDTO.class);
        } else {
            throw new RoomNotFoundException("Room not found with ID: " + roomId);
        }
    }

    @Transactional
    public RoomDTO createRoom(CreateRoomDTO roomDTO) {
        Room newRoom = EntityDTOConverter.convertToEntity(roomDTO, Room.class);
        Room savedRoom = roomRepository.save(newRoom);
        return EntityDTOConverter.convertToDTO(savedRoom, RoomDTO.class);
    }

    @Transactional
    public RoomDTO updateRoom(Long roomId, UpdateRoomDTO roomDTO) {
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        if (optionalRoom.isPresent()) {
            Room existingRoom = optionalRoom.get();
            BeanUtils.copyProperties(roomDTO, existingRoom, "id");
            Room updatedRoom = roomRepository.save(existingRoom);
            return EntityDTOConverter.convertToDTO(updatedRoom, RoomDTO.class);
        }
        else{
            throw new RoomNotFoundException("Room not found with ID: " + roomId);
        }
    }

    @Transactional
    public void deleteRoom(Long roomId) {
        if (roomRepository.existsById(roomId)) {
            roomRepository.deleteById(roomId);
        }
        else{
            throw new RoomNotFoundException("Room not found with ID: " + roomId);
        }
    }
}
