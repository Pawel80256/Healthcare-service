package com.healthcare_service.DTO.visit;

import lombok.Getter;

import java.util.UUID;

@Getter
public class BookVisitDTO {
    private UUID visitId;
    private UUID clientId;
    private String visitType;
}
