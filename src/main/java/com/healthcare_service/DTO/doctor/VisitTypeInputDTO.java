package com.healthcare_service.DTO.doctor;

import lombok.Getter;

import java.util.UUID;

@Getter
public class VisitTypeInputDTO {
    private UUID doctorId;
    private String visitType;
    private Integer visitPrice;
}
