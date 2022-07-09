package com.healthcare_service.service.visit;

import com.healthcare_service.repository.DoctorRepository;
import com.healthcare_service.DTO.visit.BookedVisitInputDTO;
import com.healthcare_service.DTO.visit.BookVisitDTO;
import com.healthcare_service.DTO.visit.NotBookedVisitInputDTO;
import com.healthcare_service.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class VisitService {
    private final VisitRepository visitRepository;
    private final DoctorRepository doctorRepository;
    private final VisitValidator visitValidator;
    private final VisitMapper visitMapper;
    private final VisitEditor visitEditor;



    public UUID addNotBookedVisit(NotBookedVisitInputDTO visitInputDTO){
        visitValidator.validateNotBookedVisitInput(visitInputDTO,doctorRepository,visitRepository);
        var visit = visitMapper.mapNotBookedVisitInputToEntity(visitInputDTO);
        visitRepository.save(visit);
        return visit.getId();
    }

    public UUID addBookedVisit(BookedVisitInputDTO dto){
        visitValidator.validateBookedVisitInput(dto);
        var visit = visitMapper.mapBookedVisitInputToEntity(dto);
        visitRepository.save(visit);
        return visit.getId();
    }


    public void bookVisit(BookVisitDTO dto){
        visitValidator.validateVisitBooking(dto);
        visitEditor.bookVisit(dto);
    }

    public void deleteVisitById(UUID visitId){
        visitRepository.deleteById(visitId);
    }

    public void cancelVisit(UUID visitId){
        visitEditor.cancelVisit(visitId);
    }


}
