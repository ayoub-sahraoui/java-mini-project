package com.hotelbooking.hotelbooking.services;

import com.hotelbooking.hotelbooking.DTO.UserDTO;
import com.hotelbooking.hotelbooking.exception.UserNotFoundException;
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


    public UserDTO getById(int id) throws UserNotFoundException{
        Optional<Admin> admin = this.adminRepository.findById(id);
        if(admin.isPresent()){
            return UserDTO.ToDTO(admin.get());
        }else
           throw new UserNotFoundException(id);
    }

    public UserDTO findByEmail(String email) throws UserNotFoundException{
        Optional<Admin>admin = this.adminRepository.findByEmail(email);
        if(admin.isPresent()){
            return UserDTO.ToDTO(admin.get());
        }else
            throw new UserNotFoundException(email);
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

    public Admin update(Admin newAdmin, int id) throws UserNotFoundException {
        return this.adminRepository.findById(id)
                .map(admin -> {
                    admin.setFirstName(newAdmin.getFirstName());
                    admin.setLastName(newAdmin.getLastName());
                    admin.setEmail(newAdmin.getEmail());
                    admin.setRole(newAdmin.getRole());
                    return adminRepository.save(admin);
                }).orElseThrow(()->new UserNotFoundException(id));
    }
    public void delete(int id) throws UserNotFoundException {
        Optional<Admin>admin = this.adminRepository.findById(id);
        if(admin.isPresent()){
             this.adminRepository.deleteById(id);
        }else
            throw new UserNotFoundException(id);
    }
}
