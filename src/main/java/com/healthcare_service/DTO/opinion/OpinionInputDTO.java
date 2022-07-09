package com.healthcare_service.DTO.opinion;

import lombok.Getter;

import java.util.UUID;

@Getter
public class OpinionInputDTO {
    private UUID clientId;
    private UUID doctorId;
    private String text;
    private Integer value;
}
