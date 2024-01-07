package com.hotelbooking.hotelbooking.modules.employee.service;

import com.hotelbooking.hotelbooking.modules.employee.model.HouseKeeperDTO;
import com.hotelbooking.hotelbooking.modules.employee.model.CreateHouseKeeperDTO;
import com.hotelbooking.hotelbooking.modules.employee.model.UpdateHouseKeeperDTO;

import com.hotelbooking.hotelbooking.modules.employee.model.HouseKeeper;
import com.hotelbooking.hotelbooking.modules.employee.model.EntityDTOConverter;
import com.hotelbooking.hotelbooking.modules.employee.repository.HouseKeeperRepository;
import com.hotelbooking.hotelbooking.modules.employee.exception.HouseKeeperNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HouseKeeperService {

    private final HouseKeeperRepository houseKeeperRepository;

    @Autowired
    public HouseKeeperService(HouseKeeperRepository houseKeeperRepository) {
        this.houseKeeperRepository = houseKeeperRepository;
    }

    @Transactional(readOnly = true)
    public List<HouseKeeperDTO> getAllHouseKeepers() {
        List<HouseKeeper> houseKeepers = houseKeeperRepository.findAll();
        return houseKeepers.stream()
                .map(houseKeeper -> EntityDTOConverter.convertToDTO(houseKeeper, HouseKeeperDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public HouseKeeperDTO getHouseKeeperById(Long houseKeeperId) {
        Optional<HouseKeeper> optionalHouseKeeper = houseKeeperRepository.findById(houseKeeperId);
        if (optionalHouseKeeper.isPresent()) {
            return EntityDTOConverter.convertToDTO(optionalHouseKeeper.get(), HouseKeeperDTO.class);
        } else {
            throw new HouseKeeperNotFoundException("HouseKeeper not found with ID: " + houseKeeperId);
        }
    }

    @Transactional
    public HouseKeeperDTO createHouseKeeper(CreateHouseKeeperDTO houseKeeperDTO) {
        HouseKeeper newHouseKeeper = EntityDTOConverter.convertToEntity(houseKeeperDTO, HouseKeeper.class);
        HouseKeeper savedHouseKeeper = houseKeeperRepository.save(newHouseKeeper);
        return EntityDTOConverter.convertToDTO(savedHouseKeeper, HouseKeeperDTO.class);
    }

    @Transactional
    public HouseKeeperDTO updateHouseKeeper(Long houseKeeperId, UpdateHouseKeeperDTO houseKeeperDTO) {
        Optional<HouseKeeper> optionalHouseKeeper = houseKeeperRepository.findById(houseKeeperId);
        if (optionalHouseKeeper.isPresent()) {
            HouseKeeper existingHouseKeeper = optionalHouseKeeper.get();
            BeanUtils.copyProperties(houseKeeperDTO, existingHouseKeeper, "id");
            HouseKeeper updatedHouseKeeper = houseKeeperRepository.save(existingHouseKeeper);
            return EntityDTOConverter.convertToDTO(updatedHouseKeeper, HouseKeeperDTO.class);
        }
        else{
            throw new HouseKeeperNotFoundException("HouseKeeper not found with ID: " + houseKeeperId);
        }
    }

    @Transactional
    public void deleteHouseKeeper(Long houseKeeperId) {
        if (houseKeeperRepository.existsById(houseKeeperId)) {
            houseKeeperRepository.deleteById(houseKeeperId);
        }
        else{
            throw new HouseKeeperNotFoundException("HouseKeeper not found with ID: " + houseKeeperId);
        }
    }
}
