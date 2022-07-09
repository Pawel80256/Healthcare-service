package com.healthcare_service.service.doctor;

import com.healthcare_service.exceptions.InvalidRegisterData;
import com.healthcare_service.repository.ClinicRepository;
import com.healthcare_service.DTO.doctor.DoctorInputDTO;
import com.healthcare_service.entity.Doctor;

import com.healthcare_service.entity.role.ERole;
import com.healthcare_service.entity.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class DoctorMapper {

    private final PasswordEncoder passwordEncoder;
    private final ClinicRepository clinicRepository;

    public Doctor mapInputToEntity(DoctorInputDTO doctorInputDTO){

        var password = passwordEncoder.encode(doctorInputDTO.getPassword());

        var clinicType = doctorInputDTO.getClinicType();
        String specialisation = switch (clinicType) {
            case "Okulistyczna" -> "OKULISTA";
            case "Dermatologiczna" -> "DERMATOLOG";
            case "Ginekologiczna" -> "GINEKOLOG";
            case "Gastroenterologiczna" -> "GASTROLOG";
            case "Neurologiczna" -> "NEUROLOG";
            case "Alergologiczna" -> "ALERGOLOG";
            default -> throw new InvalidRegisterData("Wybierz jedną z dostępnych poradni");
        };


        return Doctor.builder()
                .id(UUID.randomUUID())
                .firstName(doctorInputDTO.getFirstName())
                .lastName(doctorInputDTO.getLastName())
                .emailAddress(doctorInputDTO.getEmailAddress().trim())
                .pesel(doctorInputDTO.getPesel())
                .username(doctorInputDTO.getUsername())
                .password(password)
                .role(new Role(ERole.DOCTOR))
                .specialisation(specialisation)
                .roomNumber(doctorInputDTO.getRoomNumber())
                .aboutMe("")
                .degree(doctorInputDTO.getDegree())
                .clinic(clinicRepository.findByClinicType(doctorInputDTO.getClinicType()))
                .myEducation("")
                .myExpirience("")
                .visitTypes(new HashMap<>())
                .build();

    }
}
