package com.hotelbooking.hotelbooking.services;

import com.hotelbooking.hotelbooking.DTO.UserDTO;
import com.hotelbooking.hotelbooking.exception.UserNotFoundException;
import com.hotelbooking.hotelbooking.models.HouseKeeper;
import com.hotelbooking.hotelbooking.repositories.HouseKeeperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HouseKeeperService {

    @Autowired
    private HouseKeeperRepository houseKeeperRepository;

    public UserDTO getById(int id) throws UserNotFoundException {
        Optional<HouseKeeper> houseKeeper = this.houseKeeperRepository.findById(id);
        if(houseKeeper.isPresent()){
            return UserDTO.ToDTO(houseKeeper.get());
        }else
            throw new UserNotFoundException(id);
    }

    public UserDTO findByEmail(String email) throws UserNotFoundException {
        Optional<HouseKeeper>houseKeeper = this.houseKeeperRepository.findByEmail(email);
        if(houseKeeper.isPresent()){
            return UserDTO.ToDTO(houseKeeper.get());
        }else
            throw new UserNotFoundException(email);
    }
    public List<UserDTO> getAll(){
        List<HouseKeeper> list = this.houseKeeperRepository.findAll();
        List<UserDTO>listUserDTO = new ArrayList<>();
        if(!list.isEmpty()){
            for (HouseKeeper houseKeeper:list) {
                UserDTO userDTO = new UserDTO(
                        houseKeeper.getFirstName(),
                        houseKeeper.getLastName(),
                        houseKeeper.getEmail(),
                        houseKeeper.getRole()
                );
                listUserDTO.add(userDTO);
            }
            return listUserDTO;
        }else
            return null;
    }
    public HouseKeeper save(HouseKeeper request){
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
