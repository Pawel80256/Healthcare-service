package com.healthcare_service.service.doctor;

import com.healthcare_service.DTO.doctor.*;
import com.healthcare_service.exceptions.NotFoundException;
import com.healthcare_service.exceptions.TakenResourceException;
import com.healthcare_service.repository.ClientRepository;
import com.healthcare_service.repository.DoctorRepository;
import com.healthcare_service.exceptions.ApiRequestException;
import com.healthcare_service.service.emailAddress.EmailAddress;
import com.healthcare_service.service.emailAddress.MyEmailValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DoctorEditor {

    private final DoctorRepository doctorRepository;
    private final ClientRepository clientRepository;
    private final MyEmailValidator myEmailValidator;


    public  void editPersonalData(EditDocPersonalDataDTO editDocPersonalDataDTO){
        var doctor = doctorRepository.findById(editDocPersonalDataDTO.getDoctorId()).orElseThrow(() -> new NotFoundException("Nie znaleziono lekarza"));
        var emailAddress = editDocPersonalDataDTO.getEmailAddress().trim();
        var firstName = editDocPersonalDataDTO.getFirstName();
        var lastName = editDocPersonalDataDTO.getLastName();

        if (!emailAddress.isBlank()) {
            myEmailValidator.validate(new EmailAddress(emailAddress));

            if (doctorRepository.existsByEmailAddress(emailAddress)
            || clientRepository.existsByEmailAddress(emailAddress))
                throw new TakenResourceException("Do tego adresu email jest przypisane inne konto.");

            doctor.setEmailAddress(emailAddress);
        }
        if (!firstName.isBlank()) {
            doctor.setFirstName(firstName);
        }
        if (!lastName.isBlank()) {
            doctor.setLastName(lastName);
        }
        doctorRepository.save(doctor);
    }

    public  void editAboutMe(EditDocAboutmeDTO editDocAboutmeDTO){
        if (editDocAboutmeDTO.getAboutMe().length() > 2048)
            throw new ApiRequestException("Sekcja \"o mnie\" jest za d??uga. Maksymalna d??ugo???? to 2048 znak??w.");
        if (editDocAboutmeDTO.getMyExpirience().length() > 2048)
            throw new ApiRequestException("Sekcja \"wykszta??cenie\" jest za d??uga. Maksymalna d??ugo???? to 2048 znak??w.");
        if (editDocAboutmeDTO.getMyEducation().length() > 2048)
            throw new ApiRequestException("Sekcja \"do??wiadczenie zawodowe\" jest za d??uga. Maksymalna d??ugo???? to 2048 znak??w.");

        var doctor = doctorRepository.findById(editDocAboutmeDTO.getDoctorId()).orElseThrow(() -> new NotFoundException("Nie znaleziono lekarza"));

        if (!editDocAboutmeDTO.getAboutMe().isBlank()) {
            doctor.setAboutMe(editDocAboutmeDTO.getAboutMe());
        }
        if (!editDocAboutmeDTO.getMyEducation().isBlank()) {
            doctor.setMyEducation(editDocAboutmeDTO.getMyEducation());
        }
        if (!editDocAboutmeDTO.getMyExpirience().isBlank()) {
            doctor.setMyExpirience(editDocAboutmeDTO.getMyExpirience());
        }
        doctorRepository.save(doctor);
    }


    public String addVisistType(VisitTypeInputDTO dto){

        String visitType = dto.getVisitType();
        var doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new NotFoundException("Nie znaleziono lekarza"));

        doctor.getVisitTypes().put(visitType, dto.getVisitPrice());

        doctorRepository.save(doctor);
        return visitType;
    }

    public void deleteVisitType(DeleteVisitTypeDTO dto){
        var doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new NotFoundException("Nie znaleziono lekarza"));

        doctor.getVisitTypes().remove(dto.getVisitType());
        doctorRepository.save(doctor);
    }

}
