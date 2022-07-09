package com.healthcare_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.healthcare_service.entity.role.Role;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Builder
@Getter @Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    private UUID id;
    @Column(unique = true)
    private String pesel;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    @Column(unique = true)
    private String emailAddress;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToOne
    private Role role;

    @OneToMany(orphanRemoval = true,mappedBy = "client")
    @JsonBackReference
    private Set<Visit> visits;

    @OneToMany(orphanRemoval = true,mappedBy = "client")
    @JsonBackReference
    private Set<Opinion> opinions;
}
