package com.healthcare_service.service.visit;

import com.healthcare_service.DTO.visit.BookVisitDTO;
import com.healthcare_service.DTO.visit.BookedVisitInputDTO;
import com.healthcare_service.exceptions.InvalidVisitException;
import com.healthcare_service.exceptions.NotFoundException;
import com.healthcare_service.repository.DoctorRepository;
import com.healthcare_service.DTO.visit.NotBookedVisitInputDTO;
import com.healthcare_service.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class VisitValidator {

    private final DoctorRepository doctorRepository;
    private final VisitRepository visitRepository;

    public void validateNotBookedVisitInput(NotBookedVisitInputDTO inputDTO, DoctorRepository doctorRepository, VisitRepository visitRepository){
        if(inputDTO.getVisitDate().isBefore(LocalDateTime.now())) throw new InvalidVisitException("Nieprawidłowy termin wizyty (przeszłość)");
        if(!doctorRepository.existsById(inputDTO.getDoctorId())) throw new NotFoundException("Nie znaleziono lekarza");
        if(visitRepository.existsByVisitDateAndDoctor_Id(inputDTO.getVisitDate(),inputDTO.getDoctorId())) throw new InvalidVisitException("Ten termin znajduje sie już w twoim kalendarzu.");
    }

    public void validateBookedVisitInput(BookedVisitInputDTO inputDTO){
        var doctor = doctorRepository.findById(inputDTO.getDoctorId())
                .orElseThrow(() -> new NotFoundException("Nie znaleziono lekarza"));
        var doctorVisitTypes = doctor.getVisitTypes().keySet();
        if(!doctorVisitTypes.contains(inputDTO.getVisitType())) throw new InvalidVisitException("Ten lekarz nie oferuje takich wizyt");
    }

    public void validateVisitBooking(BookVisitDTO dto){
        var visit = visitRepository.findById(dto.getVisitId())
                .orElseThrow(()->new NotFoundException("Nie znaleziono wizyty"));
        var doctor = visit.getDoctor();

        if(visit.getVisitDate().isBefore(LocalDateTime.now())) throw new InvalidVisitException("Czas na rezerwację tego terminu minął.");

        var doctorVisitTypes = doctor.getVisitTypes().keySet();
        if(!doctorVisitTypes.contains(dto.getVisitType())) throw new InvalidVisitException("Ten lekarz nie oferuje takich wizyt");
    }

}
