package com.healthcare_service.service.client;

import com.healthcare_service.entity.Address;
import com.healthcare_service.entity.Client;
import com.healthcare_service.DTO.client.ClientInputDTO;
import com.healthcare_service.entity.role.ERole;
import com.healthcare_service.entity.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ClientMapper {

    private final PasswordEncoder passwordEncoder;

    public Client mapInputToEntity(ClientInputDTO clientInputDTO){
        var addressInputDTO = clientInputDTO.getAddressInputDTO();
        var password = passwordEncoder.encode(clientInputDTO.getPassword());
        var address = Address.builder()
                .city(addressInputDTO.getCity())
                .roadName(addressInputDTO.getRoadName())
                .buildingNumber(addressInputDTO.getBuildingNumber())
                .localNumber(addressInputDTO.getLocalNumber())
                .build();
        return Client.builder()
                .id(UUID.randomUUID())
                .pesel(clientInputDTO.getPesel())
                .firstName(clientInputDTO.getFirstName())
                .lastName(clientInputDTO.getLastName())
                .username(clientInputDTO.getUsername())
                .password(password)
                .role(new Role(ERole.CLIENT))
                .emailAddress(clientInputDTO.getEmailAddress().trim())
                .address(address)
                .build();
    }

}
