package com.healthcare_service.service.visit;

import com.healthcare_service.exceptions.NotFoundException;
import com.healthcare_service.repository.ClientRepository;
import com.healthcare_service.DTO.visit.BookVisitDTO;
import com.healthcare_service.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class VisitEditor {
    private final VisitRepository visitRepository;
    private final ClientRepository clientRepository;

    public void bookVisit(BookVisitDTO dto){
        var client = clientRepository.findById(dto.getClientId()).orElseThrow(() -> new NotFoundException("Nie znaleziono pacjenta"));
        var visit = visitRepository.findById(dto.getVisitId()).orElseThrow(()->new NotFoundException("Nie znaleziono wizyty"));


        visit.setClient(client);
        visit.setVisitType(dto.getVisitType());
        visitRepository.save(visit);

        client.getVisits().add(visit);
        clientRepository.save(client);

    }

    public void cancelVisit(UUID visitId){
        var visit = visitRepository.findById(visitId).orElseThrow(()->new NotFoundException("Nie znaleziono wizyty"));
        visit.setVisitType(null);
        visit.setClient(null);
        visitRepository.save(visit);
    }

//    public void editVisit(EditVisitDTO dto){
//        var visit = visitRepository.findById(dto.getVisitId()).orElseThrow(() -> new NotFoundException("Nie znaleziono wizyty"));
//        visit.setVisitDate(dto.getVisitDate());
//        if(!dto.getVisitType().isBlank() && !(visit.getClient()==null)){
//            visit.setVisitType(dto.getVisitType());
//        }
//        if(visit.getClient()==null && !dto.getVisitType().isBlank()) throw new ApiRequestException("Niedozwolona operacja!");
//        visitRepository.save(visit);
//    }
}
