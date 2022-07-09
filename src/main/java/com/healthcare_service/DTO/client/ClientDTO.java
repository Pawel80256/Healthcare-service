package com.healthcare_service.DTO.client;

import com.healthcare_service.DTO.address.AddressDTO;
import com.healthcare_service.entity.Address;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class ClientDTO {
    private UUID id;
    private String firstName;
    private String lastaName;
    private String emailAddress;
    private AddressDTO address;
}
