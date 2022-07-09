package com.healthcare_service.repository;

import com.healthcare_service.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HospitalRepository extends JpaRepository<Hospital, UUID> {
    Hospital findByName(String name);
}
