package com.healthcare_service.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Visit implements Comparable<Visit>{
    @Id
    private UUID id;
    private LocalDateTime visitDate;
    private String visitType;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private Client client;

    @Override
    public int compareTo(Visit o) {
        return getVisitDate().compareTo(o.getVisitDate());
    }
}
