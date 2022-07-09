package com.healthcare_service.DTO.address;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class AddressInputDTO {
    @NotBlank
    private String city;
    @NotBlank
    private String roadName;
    @NotBlank
    private String buildingNumber;
    @NotBlank
    private String localNumber;

}
