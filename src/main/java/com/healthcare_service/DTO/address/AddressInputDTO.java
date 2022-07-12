package com.healthcare_service.DTO.address;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter @Builder
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
