package com.healthcare_service.repository;

import com.healthcare_service.entity.Clinic;
import com.healthcare_service.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClinicRepository extends JpaRepository<Clinic, UUID> {
    List<Clinic> findByHospital(Hospital hospital);
    Clinic findByClinicType(String clinicType);
    Boolean existsByClinicType(String clinicType);
}
