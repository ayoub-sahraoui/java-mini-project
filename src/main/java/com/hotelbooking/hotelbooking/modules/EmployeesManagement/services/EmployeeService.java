package com.hotelbooking.hotelbooking.modules.EmployeesManagement.services;

import com.hotelbooking.hotelbooking.modules.EmployeesManagement.models.Admin;
import com.hotelbooking.hotelbooking.modules.EmployeesManagement.models.User;
import com.hotelbooking.hotelbooking.modules.EmployeesManagement.models.UserDTO;
import com.hotelbooking.hotelbooking.modules.EmployeesManagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAll(){
        List<User> list = this.userRepository.findAll();
        List<UserDTO>listUserDTO = new ArrayList<>();
        if(!list.isEmpty()){
            for (User Employee :list) {
                UserDTO userDTO = new UserDTO(
                        Employee.getFirstName(),
                        Employee.getLastName(),
                        Employee.getEmail(),
                        Employee.getRole()
                );
                listUserDTO.add(userDTO);
            }
            return listUserDTO;
        }else
            return null;
    }
}
