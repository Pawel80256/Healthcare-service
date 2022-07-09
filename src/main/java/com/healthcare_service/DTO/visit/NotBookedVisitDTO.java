package com.healthcare_service.DTO.visit;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class NotBookedVisitDTO {
    private UUID id;
    private LocalDateTime visitDate;
    private UUID doctorId;
}
