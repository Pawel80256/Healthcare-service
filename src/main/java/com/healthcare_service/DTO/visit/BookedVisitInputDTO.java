package com.healthcare_service.DTO.visit;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class BookedVisitInputDTO {
    private UUID doctorId;
    private UUID clientId;
    private LocalDateTime visitDate;
    private String visitType;
}
