package com.hotelbooking.hotelbooking.services;

import com.hotelbooking.hotelbooking.DTO.UserDTO;
import com.hotelbooking.hotelbooking.models.Admin;
import com.hotelbooking.hotelbooking.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;


    public UserDTO getById(int id){
        Optional<Admin> admin = this.adminRepository.findById(id);
        if(admin.isPresent()){
            return UserDTO.ToDTO(admin.get());
        }else
           return null;
    }

    public UserDTO findByEmail(String email){
        Optional<Admin>admin = this.adminRepository.findByEmail(email);
        if(admin.isPresent()){
            return UserDTO.ToDTO(admin.get());
        }else
            return null;
    }
    public List<UserDTO> getAll(){
        List<Admin> list = this.adminRepository.findAll();
        List<UserDTO>listUserDTO = new ArrayList<>();
        if(!list.isEmpty()){
            for (Admin admin:list) {
                UserDTO userDTO = new UserDTO(
                        admin.getFirstName(),
                        admin.getLastName(),
                        admin.getEmail(),
                        admin.getRole()
                );
                listUserDTO.add(userDTO);
            }
            return listUserDTO;
        }else
            return null;
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
