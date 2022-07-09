package com.healthcare_service.DTO.visit;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class NotBookedVisitInputDTO {
    private UUID doctorId;
    private LocalDateTime visitDate;
}
