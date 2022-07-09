package com.healthcare_service.service.client;

import com.healthcare_service.entity.Client;
import com.healthcare_service.exceptions.NotFoundException;
import com.healthcare_service.repository.ClientRepository;
import com.healthcare_service.DTO.client.ClientInputDTO;
import com.healthcare_service.entity.Visit;
import com.healthcare_service.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
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


    public UUID createClient(ClientInputDTO clientInputDTO){
        clientValidator.validateInput(clientInputDTO);
        var client = clientMapper.mapInputToEntity(clientInputDTO);
        clientRepository.save(client);
        return client.getId();
    }

    public Client getClientById(UUID clientId){
        return clientRepository.findById(clientId).orElseThrow(() -> new NotFoundException("Nie znaleziono pacjenta"));
    }

    public Client getClientByUsername(String username){
        return clientRepository.findByUsername(username);
    }

    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    public List<Visit> getBookedVisitByClientId(UUID id){
        var client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException("Nie znaleziono pacjenta"));
        var visits = visitRepository.findByClient(client);
        return visits.stream()
                .filter((visit -> visit.getVisitDate().isAfter(LocalDateTime.now()) && visit.getClient() != null))
                .sorted()
                .collect(Collectors.toList());
    }

    public List<Visit> getPastVisitsByClientId(UUID clientId){
        var client = clientRepository.findById(clientId).orElseThrow(() -> new NotFoundException("Nie znaleziono pacjenta"));
        var visits = visitRepository.findByClient(client);

        return visits.stream()
                .filter(visit -> visit.getVisitDate().isBefore(LocalDateTime.now()))
                .sorted()
                .collect(Collectors.toList());

    }

    public void deleteClientById(UUID id){
        clientRepository.deleteById(id);
    }

}
