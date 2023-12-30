package com.hotelbooking.hotelbooking.services;

import com.hotelbooking.hotelbooking.DTO.ReceptionistDTO;
import com.hotelbooking.hotelbooking.DTO.ServerDTO;
import com.hotelbooking.hotelbooking.models.Receptionist;
import com.hotelbooking.hotelbooking.models.Server;
import com.hotelbooking.hotelbooking.repositories.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServerService {

    @Autowired
    private ServerRepository serverRepository;

    public Server getById(int id){
        Optional<Server> server = this.serverRepository.findById(id);
        return server.orElse( null);
    }

    public Server findByEmail(String email){
        Optional<Server>server = this.serverRepository.findByEmail(email);
        return server.orElse(null);
    }
    public List<Server> getAll(){
        return this.serverRepository.findAll();
    }
    public Server save(ServerDTO request){
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
