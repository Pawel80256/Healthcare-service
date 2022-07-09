package com.healthcare_service.service.doctor;

import com.healthcare_service.exceptions.InvalidRegisterData;
import com.healthcare_service.exceptions.TakenResourceException;
import com.healthcare_service.repository.ClientRepository;
import com.healthcare_service.DTO.doctor.DoctorInputDTO;
import com.healthcare_service.repository.ClinicRepository;
import com.healthcare_service.repository.DoctorRepository;
import com.healthcare_service.service.emailAddress.EmailAddress;
import com.healthcare_service.service.emailAddress.MyEmailValidator;
import com.healthcare_service.service.pesel.PeselValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DoctorValidator {

    private final DoctorRepository doctorRepository;
    private final ClientRepository clientRepository;
    private final ClinicRepository clinicRepository;
    private final MyEmailValidator myEmailValidator;
    private final PeselValidator peselValidator;

    public void validateInput(DoctorInputDTO inputDTO){
        if (doctorRepository.existsByUsername(inputDTO.getUsername().trim())
                || clientRepository.existsByUsername(inputDTO.getUsername().trim())) throw new TakenResourceException("Ten login jest już zajęty");

        if (!(inputDTO.getUsername().trim().length() >= 5)) throw new InvalidRegisterData("Minimalna długość loginu to 5 znaków!");

        myEmailValidator.validate(new EmailAddress(inputDTO.getEmailAddress()));

        if (doctorRepository.existsByEmailAddress(inputDTO.getEmailAddress().trim()))
            throw new TakenResourceException("Na ten adres mailowy jest już założone konto lekarza, skontaktuj się z administratorem.");

        if (!(inputDTO.getPassword().length() >= 5)) throw new InvalidRegisterData("Minimalna dlugość hasła to 5 znaków!");

        if ((inputDTO.getFirstName().trim().isBlank())) throw new InvalidRegisterData("Wprowadź imię!");

        peselValidator.validate(inputDTO.getPesel());

        if (doctorRepository.existsByPesel(inputDTO.getPesel()))
            throw new TakenResourceException("Na ten numer pesel jest juz założone konto");

        var degrees = List.of("lek. med.","dr n. med.","dr hab n. med.","prof. dr hab");
        if(!degrees.contains(inputDTO.getDegree()))
            throw new InvalidRegisterData("Niepoprawny stopień");

        if(!clinicRepository.existsByClinicType(inputDTO.getClinicType()))
            throw new InvalidRegisterData("Niepoprawny rodzaj poradni");
    }
}
