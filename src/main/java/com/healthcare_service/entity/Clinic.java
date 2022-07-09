package com.healthcare_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class Clinic {
    @Id
    private UUID id;
    private String clinicType;

    @ManyToOne()

    private Hospital hospital;

    @OneToMany(mappedBy = "clinic")
    @JsonBackReference
    private Set<Doctor> doctors;
}
