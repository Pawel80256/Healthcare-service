package com.healthcare_service.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class Opinion {
    @Id
    private UUID id;

    @Column(length = 2048)
    private String text;
    private Integer value;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private Client client;
}
