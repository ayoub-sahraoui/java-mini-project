package com.hotelbooking.hotelbooking.modules.employee.service;

import com.hotelbooking.hotelbooking.modules.employee.model.ReceptionistDTO;
import com.hotelbooking.hotelbooking.modules.employee.model.CreateReceptionistDTO;
import com.hotelbooking.hotelbooking.modules.employee.model.UpdateReceptionistDTO;

import com.hotelbooking.hotelbooking.modules.employee.model.Receptionist;
import com.hotelbooking.hotelbooking.modules.employee.model.EntityDTOConverter;
import com.hotelbooking.hotelbooking.modules.employee.repository.ReceptionistRepository;
import com.hotelbooking.hotelbooking.modules.employee.exception.ReceptionistNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReceptionistService {

    private final ReceptionistRepository receptionistRepository;

    @Autowired
    public ReceptionistService(ReceptionistRepository receptionistRepository) {
        this.receptionistRepository = receptionistRepository;
    }

    @Transactional(readOnly = true)
    public List<ReceptionistDTO> getAllReceptionists() {
        List<Receptionist> receptionists = receptionistRepository.findAll();
        return receptionists.stream()
                .map(receptionist -> EntityDTOConverter.convertToDTO(receptionist, ReceptionistDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ReceptionistDTO getReceptionistById(Long receptionistId) {
        Optional<Receptionist> optionalReceptionist = receptionistRepository.findById(receptionistId);
        if (optionalReceptionist.isPresent()) {
            return EntityDTOConverter.convertToDTO(optionalReceptionist.get(), ReceptionistDTO.class);
        } else {
            throw new ReceptionistNotFoundException("Receptionist not found with ID: " + receptionistId);
        }
    }

    @Transactional
    public ReceptionistDTO createReceptionist(CreateReceptionistDTO receptionistDTO) {
        Receptionist newReceptionist = EntityDTOConverter.convertToEntity(receptionistDTO, Receptionist.class);
        Receptionist savedReceptionist = receptionistRepository.save(newReceptionist);
        return EntityDTOConverter.convertToDTO(savedReceptionist, ReceptionistDTO.class);
    }

    @Transactional
    public ReceptionistDTO updateReceptionist(Long receptionistId, UpdateReceptionistDTO receptionistDTO) {
        Optional<Receptionist> optionalReceptionist = receptionistRepository.findById(receptionistId);
        if (optionalReceptionist.isPresent()) {
            Receptionist existingReceptionist = optionalReceptionist.get();
            BeanUtils.copyProperties(receptionistDTO, existingReceptionist, "id");
            Receptionist updatedReceptionist = receptionistRepository.save(existingReceptionist);
            return EntityDTOConverter.convertToDTO(updatedReceptionist, ReceptionistDTO.class);
        }
        else{
            throw new ReceptionistNotFoundException("Receptionist not found with ID: " + receptionistId);
        }
    }

    @Transactional
    public void deleteReceptionist(Long receptionistId) {
        if (receptionistRepository.existsById(receptionistId)) {
            receptionistRepository.deleteById(receptionistId);
        }
        else{
            throw new ReceptionistNotFoundException("Receptionist not found with ID: " + receptionistId);
        }
    }
}
