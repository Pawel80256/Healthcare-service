package com.healthcare_service.service.opinion;

import com.healthcare_service.DTO.opinion.OpinionInputDTO;
import com.healthcare_service.exceptions.InvalidOpinionException;
import com.healthcare_service.repository.OpinionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OpinionValidator {
    private final OpinionRepository opinionRepository;

    public void validateOpinionInput(OpinionInputDTO dto){
        if (dto.getValue()>5 || dto.getValue()<1)
            throw new InvalidOpinionException("Opinia musi miec wartość od 1 do 5");

        if(opinionRepository.existsByClient_IdAndDoctor_Id(dto.getClientId(),dto.getDoctorId()))
            throw new InvalidOpinionException("Nie możesz dodać więcej niż jednej opinii do jednego lekarza!");

        if(dto.getText().length()>2048)
            throw new InvalidOpinionException("Opinia jest zbyt długa. Możesz umieścić maksymalnie 2048 znaków.");
    }
}
