package com.healthcare_service.controller;

import com.healthcare_service.entity.Client;
import com.healthcare_service.DTO.client.ClientInputDTO;
import com.healthcare_service.entity.Visit;
import com.healthcare_service.service.client.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class ClientController {
    private final ClientService clientService;

    @PostMapping("/client")
    public ResponseEntity<UUID> addClient(@RequestBody ClientInputDTO clientInputDTO){
        var clientId = clientService.createClient(clientInputDTO);
        return new ResponseEntity<>(clientId, HttpStatus.CREATED);
    }

    @GetMapping("/client")
    public ResponseEntity<List<Client>> getClients(){
        var clients = clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<Client> getClientById(@PathVariable UUID clientId){
        var client = clientService.getClientById(clientId);
        return new ResponseEntity<>(client,HttpStatus.OK);
    }

    @GetMapping("clientByUsername/{clientUsername}")
    public ResponseEntity<Client> getClientByUsername(@PathVariable String clientUsername){
        var client = clientService.getClientByUsername(clientUsername);
        return new ResponseEntity<>(client,HttpStatus.OK);
    }

    @GetMapping("client/{clientId}/visitsHistory")
    public ResponseEntity<List<Visit>> getPastVisitsByClientId(@PathVariable UUID clientId){
        var pastVisits = clientService.getPastVisitsByClientId(clientId);
        return new ResponseEntity<>(pastVisits, HttpStatus.OK);
    }

    @GetMapping("client/{clientId}/bookedVisits")
    public ResponseEntity<List<Visit>> getBookedVisitsByClientId(@PathVariable UUID clientId){
        var bookedVisits = clientService.getBookedVisitByClientId(clientId);
        return new ResponseEntity<>(bookedVisits, HttpStatus.OK);
    }


    @DeleteMapping("/client/{id}")
    public ResponseEntity<Void> deleteClientById(@PathVariable UUID id){
        clientService.deleteClientById(id);
        return ResponseEntity.ok().build();
    }

}
