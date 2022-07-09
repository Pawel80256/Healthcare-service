package com.healthcare_service.repository;

import com.healthcare_service.entity.Client;
import com.healthcare_service.entity.Doctor;
import com.healthcare_service.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface VisitRepository extends JpaRepository<Visit, UUID> {
    List<Visit> findByClient(Client client);
    List<Visit> findByDoctor(Doctor doctor);
    boolean existsByVisitDateAndDoctor_Id(LocalDateTime visitDate, UUID doctorId);
}
