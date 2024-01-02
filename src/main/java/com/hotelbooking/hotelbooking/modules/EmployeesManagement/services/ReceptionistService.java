package com.hotelbooking.hotelbooking.modules.EmployeesManagement.services;

import com.hotelbooking.hotelbooking.modules.EmployeesManagement.models.Receptionist;
import com.hotelbooking.hotelbooking.modules.EmployeesManagement.models.UserDTO;
import com.hotelbooking.hotelbooking.exception.UserNotFoundException;
import com.hotelbooking.hotelbooking.modules.EmployeesManagement.repositories.ReceptionistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReceptionistService {

    @Autowired
     private ReceptionistRepository receptionistRepository;


     public UserDTO getById(int id) throws UserNotFoundException {
         Optional<Receptionist>receptionist = this.receptionistRepository.findById(id);
         if(receptionist.isPresent()){
             return UserDTO.ToDTO(receptionist.get());
         }else
             throw new UserNotFoundException(id);
     }

     public UserDTO findByEmail(String email) throws UserNotFoundException {
         Optional<Receptionist>receptionist = this.receptionistRepository.findByEmail(email);
         if(receptionist.isPresent()){
             return UserDTO.ToDTO(receptionist.get());
         }else
             throw new UserNotFoundException(email);
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

    public Receptionist update(Receptionist newReceptionist, int id) throws UserNotFoundException {
        return this.receptionistRepository.findById(id)
                .map(admin -> {
                    admin.setFirstName(newReceptionist.getFirstName());
                    admin.setLastName(newReceptionist.getLastName());
                    admin.setEmail(newReceptionist.getEmail());
                    admin.setRole(newReceptionist.getRole());
                    return receptionistRepository.save(admin);
                }).orElseThrow(()->new UserNotFoundException(id));
    }

    public String delete(int id){
         this.receptionistRepository.deleteById(id);
         return "Receptionist deleted";
    }
}
