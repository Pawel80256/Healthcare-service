package com.healthcare_service.DTO.visit;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class EditVisitDTO {
    private UUID visitId;
    private LocalDateTime visitDate;
    private String visitType;
}
