package com.hotelbooking.hotelbooking.modules.employee.service;

import com.hotelbooking.hotelbooking.modules.employee.model.ServerDTO;
import com.hotelbooking.hotelbooking.modules.employee.model.CreateServerDTO;
import com.hotelbooking.hotelbooking.modules.employee.model.UpdateServerDTO;

import com.hotelbooking.hotelbooking.modules.employee.model.Server;
import com.hotelbooking.hotelbooking.modules.employee.model.EntityDTOConverter;
import com.hotelbooking.hotelbooking.modules.employee.repository.ServerRepository;
import com.hotelbooking.hotelbooking.modules.employee.exception.ServerNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServerService {

    private final ServerRepository serverRepository;

    @Autowired
    public ServerService(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    @Transactional(readOnly = true)
    public List<ServerDTO> getAllServers() {
        List<Server> servers = serverRepository.findAll();
        return servers.stream()
                .map(server -> EntityDTOConverter.convertToDTO(server, ServerDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ServerDTO getServerById(Long serverId) {
        Optional<Server> optionalServer = serverRepository.findById(serverId);
        if (optionalServer.isPresent()) {
            return EntityDTOConverter.convertToDTO(optionalServer.get(), ServerDTO.class);
        } else {
            throw new ServerNotFoundException("Server not found with ID: " + serverId);
        }
    }

    @Transactional
    public ServerDTO createServer(CreateServerDTO serverDTO) {
        Server newServer = EntityDTOConverter.convertToEntity(serverDTO, Server.class);
        Server savedServer = serverRepository.save(newServer);
        return EntityDTOConverter.convertToDTO(savedServer, ServerDTO.class);
    }

    @Transactional
    public ServerDTO updateServer(Long serverId, UpdateServerDTO serverDTO) {
        Optional<Server> optionalServer = serverRepository.findById(serverId);
        if (optionalServer.isPresent()) {
            Server existingServer = optionalServer.get();
            BeanUtils.copyProperties(serverDTO, existingServer, "id");
            Server updatedServer = serverRepository.save(existingServer);
            return EntityDTOConverter.convertToDTO(updatedServer, ServerDTO.class);
        }
        else{
            throw new ServerNotFoundException("Server not found with ID: " + serverId);
        }
    }

    @Transactional
    public void deleteServer(Long serverId) {
        if (serverRepository.existsById(serverId)) {
            serverRepository.deleteById(serverId);
        }
        else{
            throw new ServerNotFoundException("Server not found with ID: " + serverId);
        }
    }
}
