package com.healthcare_service.DTO.visit;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class BookedVisitDTO {
    private UUID id;
    private LocalDateTime visitDate;
    private String visitType;
    private UUID doctorId;
    private UUID clientId;
}
