package com.hotelbooking.hotelbooking.services;

import com.hotelbooking.hotelbooking.DTO.HouseKeeperDTO;
import com.hotelbooking.hotelbooking.DTO.ReceptionistDTO;
import com.hotelbooking.hotelbooking.models.HouseKeeper;
import com.hotelbooking.hotelbooking.models.Receptionist;
import com.hotelbooking.hotelbooking.repositories.HouseKeeperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HouseKeeperService {

    @Autowired
    private HouseKeeperRepository houseKeeperRepository;

    public HouseKeeper getById(int id){
        Optional<HouseKeeper> houseKeeper = this.houseKeeperRepository.findById(id);
        return houseKeeper.orElse( null);
    }

    public HouseKeeper findByEmail(String email){
        Optional<HouseKeeper>houseKeeper = this.houseKeeperRepository.findByEmail(email);
        return houseKeeper.orElse(null);
    }
    public List<HouseKeeper> getAll(){
        return this.houseKeeperRepository.findAll();
    }
    public HouseKeeper save(HouseKeeperDTO request){
        HouseKeeper houseKeeper = new HouseKeeper(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                request.getRole()
        );
        return this.houseKeeperRepository.save(houseKeeper);
    }

    public String delete(int id){
        this.houseKeeperRepository.deleteById(id);
        return "House Keeper deleted";
    }
}
