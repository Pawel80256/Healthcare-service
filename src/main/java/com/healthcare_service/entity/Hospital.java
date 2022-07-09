package com.healthcare_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class Hospital {
    @Id
    private UUID id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "hospital")
    @JsonBackReference
    private Set<Clinic> clinics;
}
