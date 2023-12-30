package com.hotelbooking.hotelbooking.services;

import com.hotelbooking.hotelbooking.DTO.ReceptionistDTO;
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


     public Receptionist getById(int id){
         Optional<Receptionist>receptionist = this.receptionistRepository.findById(id);
         return receptionist.orElse( null);
     }

     public Receptionist findByEmail(String email){
         Optional<Receptionist>receptionist = this.receptionistRepository.findByEmail(email);
         return receptionist.orElse(null);
     }
    public List<Receptionist>getAll(){
         return this.receptionistRepository.findAll();
    }
    public Receptionist save(ReceptionistDTO request){
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
