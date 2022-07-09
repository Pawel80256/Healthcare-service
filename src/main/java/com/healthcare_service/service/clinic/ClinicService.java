package com.healthcare_service.service.clinic;

import com.healthcare_service.entity.Clinic;
import com.healthcare_service.repository.ClinicRepository;
import com.healthcare_service.entity.Doctor;
import com.healthcare_service.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClinicService {
    private final ClinicRepository clinicRepository;
    private final DoctorRepository doctorRepository;

    public List<Clinic> findAllClinics(){
        return clinicRepository.findAll();
    }
    public Clinic findClinicById(UUID id){
        return clinicRepository.findById(id).get();
    }
    public List<Doctor> findDoctorsByClinicId(UUID clinicId){
        return doctorRepository.findByClinicId(clinicId);
    }
}
