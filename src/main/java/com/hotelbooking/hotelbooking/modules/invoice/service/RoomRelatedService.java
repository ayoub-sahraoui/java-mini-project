package com.hotelbooking.hotelbooking.modules.invoice.service;

import com.hotelbooking.hotelbooking.modules.invoice.model.RoomRelatedDTO;
import com.hotelbooking.hotelbooking.modules.invoice.model.CreateRoomRelatedDTO;
import com.hotelbooking.hotelbooking.modules.invoice.model.UpdateRoomRelatedDTO;

import com.hotelbooking.hotelbooking.modules.invoice.model.RoomRelated;
import com.hotelbooking.hotelbooking.modules.invoice.model.EntityDTOConverter;
import com.hotelbooking.hotelbooking.modules.invoice.repository.RoomRelatedRepository;
import com.hotelbooking.hotelbooking.modules.invoice.exception.RoomRelatedNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomRelatedService {

    private final RoomRelatedRepository roomRelatedRepository;

    @Autowired
    public RoomRelatedService(RoomRelatedRepository roomRelatedRepository) {
        this.roomRelatedRepository = roomRelatedRepository;
    }

    @Transactional(readOnly = true)
    public List<RoomRelatedDTO> getAllRoomRelateds() {
        List<RoomRelated> roomRelateds = roomRelatedRepository.findAll();
        return roomRelateds.stream()
                .map(roomRelated -> EntityDTOConverter.convertToDTO(roomRelated, RoomRelatedDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RoomRelatedDTO getRoomRelatedById(Long roomRelatedId) {
        Optional<RoomRelated> optionalRoomRelated = roomRelatedRepository.findById(roomRelatedId);
        if (optionalRoomRelated.isPresent()) {
            return EntityDTOConverter.convertToDTO(optionalRoomRelated.get(), RoomRelatedDTO.class);
        } else {
            throw new RoomRelatedNotFoundException("RoomRelated not found with ID: " + roomRelatedId);
        }
    }

    @Transactional
    public RoomRelatedDTO createRoomRelated(CreateRoomRelatedDTO roomRelatedDTO) {
        RoomRelated newRoomRelated = EntityDTOConverter.convertToEntity(roomRelatedDTO, RoomRelated.class);
        RoomRelated savedRoomRelated = roomRelatedRepository.save(newRoomRelated);
        return EntityDTOConverter.convertToDTO(savedRoomRelated, RoomRelatedDTO.class);
    }

    @Transactional
    public RoomRelatedDTO updateRoomRelated(Long roomRelatedId, UpdateRoomRelatedDTO roomRelatedDTO) {
        Optional<RoomRelated> optionalRoomRelated = roomRelatedRepository.findById(roomRelatedId);
        if (optionalRoomRelated.isPresent()) {
            RoomRelated existingRoomRelated = optionalRoomRelated.get();
            BeanUtils.copyProperties(roomRelatedDTO, existingRoomRelated, "id");
            RoomRelated updatedRoomRelated = roomRelatedRepository.save(existingRoomRelated);
            return EntityDTOConverter.convertToDTO(updatedRoomRelated, RoomRelatedDTO.class);
        }
        else{
            throw new RoomRelatedNotFoundException("RoomRelated not found with ID: " + roomRelatedId);
        }
    }

    @Transactional
    public void deleteRoomRelated(Long roomRelatedId) {
        if (roomRelatedRepository.existsById(roomRelatedId)) {
            roomRelatedRepository.deleteById(roomRelatedId);
        }
        else{
            throw new RoomRelatedNotFoundException("RoomRelated not found with ID: " + roomRelatedId);
        }
    }
}
