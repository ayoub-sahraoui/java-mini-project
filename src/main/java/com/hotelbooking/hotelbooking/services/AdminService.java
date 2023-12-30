package com.hotelbooking.hotelbooking.services;

import com.hotelbooking.hotelbooking.DTO.AdminDTO;
import com.hotelbooking.hotelbooking.DTO.ReceptionistDTO;
import com.hotelbooking.hotelbooking.models.Admin;
import com.hotelbooking.hotelbooking.models.Receptionist;
import com.hotelbooking.hotelbooking.repositories.AdminRepository;
import com.hotelbooking.hotelbooking.repositories.ReceptionistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;


    public AdminDTO getById(int id){
        Optional<Admin> admin = this.adminRepository.findById(id);
        if(admin.isPresent()){
            return AdminDTO.ToDTO(admin.get());
        }else
            return null;
    }

    public Admin findByEmail(String email){
        Optional<Admin>admin = this.adminRepository.findByEmail(email);
        return admin.orElse(null);
    }
    public List<Admin> getAll(){
        return this.adminRepository.findAll();
    }
    public Admin save(Admin request){
        Admin admin = new Admin(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                request.getRole()
        );
        return this.adminRepository.save(admin);
    }

    public String delete(int id){
        this.adminRepository.deleteById(id);
        return "Receptionist deleted";
    }
}
