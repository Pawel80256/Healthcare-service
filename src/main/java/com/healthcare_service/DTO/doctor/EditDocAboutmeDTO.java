package com.healthcare_service.DTO.doctor;

import lombok.Getter;

import java.util.UUID;

@Getter
public class EditDocAboutmeDTO {
    private UUID doctorId;
    private String aboutMe;
    private String myEducation;
    private String myExpirience;
}
