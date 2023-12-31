package com.hotelbooking.hotelbooking.services;

import com.hotelbooking.hotelbooking.DTO.ReceptionistDTO;
import com.hotelbooking.hotelbooking.DTO.UserDTO;
import com.hotelbooking.hotelbooking.models.Admin;
import com.hotelbooking.hotelbooking.models.Receptionist;
import com.hotelbooking.hotelbooking.repositories.ReceptionistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReceptionistService {

    @Autowired
     private ReceptionistRepository receptionistRepository;


     public UserDTO getById(int id){
         Optional<Receptionist>receptionist = this.receptionistRepository.findById(id);
         if(receptionist.isPresent()){
             return UserDTO.ToDTO(receptionist.get());
         }else
             return null;
     }

     public UserDTO findByEmail(String email){
         Optional<Receptionist>receptionist = this.receptionistRepository.findByEmail(email);
         if(receptionist.isPresent()){
             return UserDTO.ToDTO(receptionist.get());
         }else
             return null;
     }
    public List<UserDTO>getAll(){
        List<Receptionist> list = this.receptionistRepository.findAll();
        List<UserDTO>listUserDTO = new ArrayList<>();
        if(!list.isEmpty()){
            for (Receptionist receptionist:list) {
                UserDTO userDTO = new UserDTO(
                        receptionist.getFirstName(),
                        receptionist.getLastName(),
                        receptionist.getEmail(),
                        receptionist.getRole()
                );
                listUserDTO.add(userDTO);
            }
            return listUserDTO;
        }else
            return null;
    }
    public Receptionist save(Receptionist request){
         Receptionist receptionist = new Receptionist(
                 request.getFirstName(),
                 request.getLastName(),
                 request.getEmail(),
                 request.getPassword(),
                 request.getRole()
         );
         return this.receptionistRepository.save(receptionist);
    }

    public String delete(int id){
         this.receptionistRepository.deleteById(id);
         return "Receptionist deleted";
    }
}
