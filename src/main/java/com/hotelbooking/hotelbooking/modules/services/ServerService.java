package com.hotelbooking.hotelbooking.modules.services;

import com.hotelbooking.hotelbooking.modules.models.Server;
import com.hotelbooking.hotelbooking.modules.models.UserDTO;
import com.hotelbooking.hotelbooking.exception.UserNotFoundException;
import com.hotelbooking.hotelbooking.modules.repositories.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServerService {

    @Autowired
    private ServerRepository serverRepository;

    public UserDTO getById(int id) throws UserNotFoundException {
        Optional<Server> server = this.serverRepository.findById(id);
        if(server.isPresent()){
            return UserDTO.ToDTO(server.get());
        }else
            throw new UserNotFoundException(id);
    }

    public UserDTO findByEmail(String email) throws UserNotFoundException {
        Optional<Server>server = this.serverRepository.findByEmail(email);
        if(server.isPresent()){
            return UserDTO.ToDTO(server.get());
        }else
            throw new UserNotFoundException(email);
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

    public Server update(Server newServer, int id) throws UserNotFoundException {
        return this.serverRepository.findById(id)
                .map(admin -> {
                    admin.setFirstName(newServer.getFirstName());
                    admin.setLastName(newServer.getLastName());
                    admin.setEmail(newServer.getEmail());
                    admin.setRole(newServer.getRole());
                    return serverRepository.save(admin);
                }).orElseThrow(()->new UserNotFoundException(id));
    }
    public String delete(int id){
        this.serverRepository.deleteById(id);
        return "Server deleted";
    }
}