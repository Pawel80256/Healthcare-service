package com.healthcare_service.service.clinic;

import com.healthcare_service.DTO.doctor.DoctorDTO;
import com.healthcare_service.entity.Clinic;
import com.healthcare_service.repository.ClinicRepository;
import com.healthcare_service.entity.Doctor;
import com.healthcare_service.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClinicService {
    private final ClinicRepository clinicRepository;
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    public List<Clinic> findAllClinics(){
        return clinicRepository.findAll();
    }
    public Clinic findClinicById(UUID id){
        return clinicRepository.findById(id).get();
    }
    public List<DoctorDTO> findDoctorsByClinicId(UUID clinicId){
        var doctors = doctorRepository.findByClinicId(clinicId);
        return doctors.stream()
                .map(doctor -> modelMapper.map(doctor,DoctorDTO.class))
                .collect(Collectors.toList());
    }
}
