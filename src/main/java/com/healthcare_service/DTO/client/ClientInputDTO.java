package com.healthcare_service.DTO.client;

import com.healthcare_service.DTO.address.AddressInputDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
public class ClientInputDTO {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String emailAddress;
    @NotBlank
    private String pesel;
    @NotBlank
    private AddressInputDTO addressInputDTO;
}
