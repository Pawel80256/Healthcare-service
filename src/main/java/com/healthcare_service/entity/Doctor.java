package com.healthcare_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.healthcare_service.entity.role.Role;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter @Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    @Id
    private UUID id;
    @Column(unique = true)
    private String pesel;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String emailAddress;
    private String username;
    private String password;
    private Integer roomNumber;
    @Column(length = 2048)
    private String aboutMe;
    @Column(length = 2048)
    private String myEducation;
    @Column(length = 2048)
    private String myExpirience;
    private String specialisation;
    private String degree;

    public Double getRating(){
        var doctorOpinions = this.getOpinions();
        if (doctorOpinions.size() == 0) {
            return 0.0;
        }

        double average = 0;
        for (Opinion opinion : doctorOpinions) {
            average += opinion.getValue();
        }
        return average / doctorOpinions.size();
    }

    @ManyToOne
    private Clinic clinic;

//    @ManyToMany
//    @JoinTable(name ="specialisations_doctors",
//            joinColumns = @JoinColumn(name = "doctor_id"),
//            inverseJoinColumns = @JoinColumn(name = "specialisation_id")
//    )
//    private Set<Specialisation> specialisations;

    @OneToOne
    private Role role;// = new ArrayList<>();

    @OneToMany(orphanRemoval = true, mappedBy="doctor")
    @JsonBackReference
    private Set<Visit> visits;

    @ElementCollection
    @MapKeyColumn(name = "visitType")
    @Column(name = "price")
    @CollectionTable(name = "visitTypes", joinColumns = @JoinColumn(name = "doctor_id"))
    private Map<String,Integer> visitTypes;

    @OneToMany(orphanRemoval = true,mappedBy = "doctor")
    @JsonIgnoreProperties("doctor")
    private Set<Opinion> opinions;
}
