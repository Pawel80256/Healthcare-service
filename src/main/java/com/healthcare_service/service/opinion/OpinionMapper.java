package com.healthcare_service.service.opinion;

import com.healthcare_service.exceptions.NotFoundException;
import com.healthcare_service.repository.ClientRepository;
import com.healthcare_service.repository.DoctorRepository;
import com.healthcare_service.DTO.opinion.OpinionInputDTO;
import com.healthcare_service.entity.Opinion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OpinionMapper {
    private final DoctorRepository doctorRepository;
    private final ClientRepository clientRepository;

    public Opinion mapInputToEntity(OpinionInputDTO opinionInputDTO){
        return Opinion.builder()
                .id(UUID.randomUUID())
                .client(clientRepository.findById(opinionInputDTO.getClientId()).orElseThrow(()->new NotFoundException("Nie znaleziono pacjenta")))
                .doctor(doctorRepository.findById(opinionInputDTO.getDoctorId()).orElseThrow(()->new NotFoundException("Nie znaleziono lekarza")))
                .text(opinionInputDTO.getText())
                .value(opinionInputDTO.getValue()).build();

    }
}
