package com.healthcare_service.repository;

import com.healthcare_service.entity.Client;
import com.healthcare_service.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    boolean existsById(UUID clientId);
    boolean existsByPesel(String pesel);
    boolean existsByUsername(String username);
    boolean existsByEmailAddress(String emailAddress);
    Client findByVisitsContaining(Visit visit);
    Client findByUsername(String username);
}
