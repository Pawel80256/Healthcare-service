package com.healthcare_service.service.client;

import com.healthcare_service.DTO.client.ClientDTO;
import com.healthcare_service.DTO.visit.BookedVisitDTO;
import com.healthcare_service.entity.Client;
import com.healthcare_service.exceptions.NotFoundException;
import com.healthcare_service.repository.ClientRepository;
import com.healthcare_service.DTO.client.ClientInputDTO;
import com.healthcare_service.entity.Visit;
import com.healthcare_service.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final VisitRepository visitRepository;
    private final ClientValidator clientValidator;
    private final ModelMapper modelMapper;

    public UUID createClient(ClientInputDTO clientInputDTO){
        clientValidator.validateInput(clientInputDTO);
        var client = clientMapper.mapInputToEntity(clientInputDTO);
        clientRepository.save(client);
        return client.getId();
    }

    public ClientDTO getClientById(UUID clientId){
        var client = clientRepository.findById(clientId).orElseThrow(() -> new NotFoundException("Nie znaleziono pacjenta"));
        return modelMapper.map(client,ClientDTO.class);
    }

    public ClientDTO getClientByUsername(String username){
        var client = clientRepository.findByUsername(username);
        return modelMapper.map(client, ClientDTO.class);
    }

    public List<ClientDTO> getAllClients(){
        return clientRepository.findAll().stream()
                .map(client -> modelMapper.map(client,ClientDTO.class))
                .collect(Collectors.toList());
    }

    public List<BookedVisitDTO> getBookedVisitByClientId(UUID id){
        var client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException("Nie znaleziono pacjenta"));

        var visits = visitRepository.findByClient(client).stream()
                .filter((visit -> visit.getVisitDate().isAfter(LocalDateTime.now()) && visit.getClient() != null))
                .sorted()
                .collect(Collectors.toList());

        return visits.stream()
                .map(visit -> modelMapper.map(visit,BookedVisitDTO.class))
                .collect(Collectors.toList());
    }

    public List<BookedVisitDTO> getPastVisitsByClientId(UUID clientId){
        var client = clientRepository.findById(clientId).orElseThrow(() -> new NotFoundException("Nie znaleziono pacjenta"));

        var visits = visitRepository.findByClient(client).stream()
                .filter(visit -> visit.getVisitDate().isBefore(LocalDateTime.now()))
                .sorted()
                .collect(Collectors.toList());

        return visits.stream()
                .map(visit -> modelMapper.map(visit,BookedVisitDTO.class))
                .collect(Collectors.toList());

    }

    public void deleteClientById(UUID id){
        clientRepository.deleteById(id);
    }

}
