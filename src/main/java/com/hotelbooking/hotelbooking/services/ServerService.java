package com.hotelbooking.hotelbooking.services;

import com.hotelbooking.hotelbooking.DTO.UserDTO;
import com.hotelbooking.hotelbooking.models.Server;
import com.hotelbooking.hotelbooking.repositories.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServerService {

    @Autowired
    private ServerRepository serverRepository;

    public UserDTO getById(int id){
        Optional<Server> server = this.serverRepository.findById(id);
        if(server.isPresent()){
            return UserDTO.ToDTO(server.get());
        }else
            return null;
    }

    public UserDTO findByEmail(String email){
        Optional<Server>server = this.serverRepository.findByEmail(email);
        if(server.isPresent()){
            return UserDTO.ToDTO(server.get());
        }else
            return null;
    }
    public List<UserDTO> getAll(){
        List<Server> list = this.serverRepository.findAll();
        List<UserDTO>listUserDTO = new ArrayList<>();
        if(!list.isEmpty()){
            for (Server server:list) {
                UserDTO userDTO = new UserDTO(
                        server.getFirstName(),
                        server.getLastName(),
                        server.getEmail(),
                        server.getRole()
                );
                listUserDTO.add(userDTO);
            }
            return listUserDTO;
        }else
            return null;
    }
    public Server save(Server request){
        Server server = new Server(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                request.getRole()
        );
        return this.serverRepository.save(server);
    }

    public String delete(int id){
        this.serverRepository.deleteById(id);
        return "Server deleted";
    }
}
