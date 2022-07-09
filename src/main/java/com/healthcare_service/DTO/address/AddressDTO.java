package com.healthcare_service.DTO.address;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class AddressDTO {
    private UUID id;
    private String city;
    private String roadName;
    private String buildingNumber;
    private String localNumber;
}
