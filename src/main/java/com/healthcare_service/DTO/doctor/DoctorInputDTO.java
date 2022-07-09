package com.healthcare_service.DTO.doctor;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class DoctorInputDTO {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String emailAddress;
    @NotBlank
    private String pesel;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private Integer roomNumber;
    @NotBlank
    private String degree;
    @NotBlank
    private String clinicType;
}
