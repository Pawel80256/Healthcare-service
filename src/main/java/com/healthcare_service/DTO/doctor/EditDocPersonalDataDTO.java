package com.healthcare_service.DTO.doctor;

import lombok.Getter;

import java.util.UUID;

@Getter
public class EditDocPersonalDataDTO {
    private UUID doctorId;
    private String emailAddress;
    private String firstName;
    private String lastName;

}
