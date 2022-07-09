package com.healthcare_service.DTO.doctor;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class DoctorDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private Integer roomNumber;
    private String aboutMe;
    private String myEducation;
    private String myExpirience;
    private String specialisation;
    private String degree;
    private UUID clinicId;
}
