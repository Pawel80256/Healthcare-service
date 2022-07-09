package com.healthcare_service.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class Address {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private String city;
    private String roadName;
    private String buildingNumber;
    private String localNumber;



}
