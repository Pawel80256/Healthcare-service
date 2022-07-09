package com.healthcare_service.service.visit;

import com.healthcare_service.DTO.visit.BookedVisitInputDTO;
import com.healthcare_service.DTO.visit.NotBookedVisitInputDTO;
import com.healthcare_service.entity.Visit;
import com.healthcare_service.exceptions.NotFoundException;
import com.healthcare_service.repository.ClientRepository;
import com.healthcare_service.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class VisitMapper {

    private final DoctorRepository doctorRepository;
    private final ClientRepository clientRepository;

    public Visit mapNotBookedVisitInputToEntity(NotBookedVisitInputDTO dto){
        var doctor = doctorRepository.findById(dto.getDoctorId()).orElseThrow(() -> new NotFoundException("Nie znaleziono lekarza"));
        return  Visit.builder()
                .id(UUID.randomUUID())
                .doctor(doctor)
                .visitDate(dto.getVisitDate())
                .build();
    }

    public  Visit mapBookedVisitInputToEntity(BookedVisitInputDTO dto){

            var doctor = doctorRepository.findById(dto.getDoctorId()).orElseThrow(()->new NotFoundException("Nie znaleziono lekarza"));
            var client = clientRepository.findById(dto.getClientId()).orElseThrow(()->new NotFoundException("Nie znaleziono pacjenta"));
            return Visit.builder()
                    .id(UUID.randomUUID())
                    .visitDate(dto.getVisitDate())
                    .visitType(dto.getVisitType())
                    .doctor(doctor)
                    .client(client)
                    .build();

    }
}
