package com.healthcare_service.DTO.opinion;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class OpinionDTO {
    private UUID id;
    private Integer value;
    private String text;
    private UUID doctorId;
    private UUID clientId;
}
