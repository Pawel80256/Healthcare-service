package com.healthcare_service.entity;

import com.healthcare_service.entity.role.Role;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Admin {
    @Id
    private UUID id;
    private String username;
    private String password;

    @OneToOne
    private Role role;
}
