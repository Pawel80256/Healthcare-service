package com.healthcare_service.DTO.opinion;

import lombok.Getter;

import java.util.UUID;

@Getter
public class EditOpinionDTO {
    private UUID opinionId;
    private Integer value;
    private String text;
}
