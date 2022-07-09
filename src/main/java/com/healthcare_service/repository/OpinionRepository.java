package com.healthcare_service.repository;

import com.healthcare_service.entity.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OpinionRepository extends JpaRepository<Opinion, UUID> {
    Boolean existsByClientUsernameAndDoctorUsername(String clientUsername, String doctorUsername);
    Boolean existsByClient_IdAndDoctor_Id(UUID clientId, UUID doctorId);
    List<Opinion> findByDoctorId(UUID doctorId);
}
