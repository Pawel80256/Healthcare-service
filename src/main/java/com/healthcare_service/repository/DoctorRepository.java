package com.healthcare_service.repository;

import com.healthcare_service.entity.Doctor;
import com.healthcare_service.entity.Opinion;
import com.healthcare_service.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor,UUID> {
    boolean existsById(UUID doctorId);
    boolean existsByPesel(String pesel);
    boolean existsByUsername(String username);
    boolean existsByEmailAddress(String emailAddress);
    List<Doctor> findByClinicId(UUID clinicId);
    Doctor findByUsername(String username);
    Doctor findByVisitsContaining(Visit visit);
    Doctor findByOpinionsContaining(Opinion opinion);
}
