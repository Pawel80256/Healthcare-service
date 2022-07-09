package com.healthcare_service.service.client;

import com.healthcare_service.exceptions.InvalidRegisterData;
import com.healthcare_service.exceptions.TakenResourceException;
import com.healthcare_service.repository.ClientRepository;
import com.healthcare_service.DTO.client.ClientInputDTO;
import com.healthcare_service.repository.DoctorRepository;
import com.healthcare_service.service.emailAddress.EmailAddress;
import com.healthcare_service.service.emailAddress.MyEmailValidator;
import com.healthcare_service.service.pesel.PeselValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientValidator {

    private final ClientRepository clientRepository;
    private final DoctorRepository doctorRepository;
    private final PeselValidator peselValidator;
    private final MyEmailValidator myEmailValidator;


    public void validateInput(ClientInputDTO inputDTO) {

        if (clientRepository.existsByUsername(inputDTO.getUsername())
                || doctorRepository.existsByUsername(inputDTO.getUsername()))
            throw new TakenResourceException("Ten login jest zajęty");

        if (!(inputDTO.getUsername().trim().length() >= 5))
            throw new InvalidRegisterData("Minimalna długość loginu to 5 znaków!");

        if (!(inputDTO.getPassword().length() >= 5)) throw new InvalidRegisterData("Minimalna dlugość hasła to 5 znaków!");

        peselValidator.validate(inputDTO.getPesel());

        if (clientRepository.existsByPesel(inputDTO.getPesel()))
            throw new TakenResourceException("Na ten numer pesel jest juz założone konto.");

        myEmailValidator.validate(new EmailAddress(inputDTO.getEmailAddress()));

        if (clientRepository.existsByEmailAddress(inputDTO.getEmailAddress())
                || doctorRepository.existsByEmailAddress(inputDTO.getEmailAddress()))
            throw new TakenResourceException("Na ten adres mailowy jest już założone konto.");

        if(inputDTO.getFirstName().isBlank())
            throw new InvalidRegisterData("Wprowadź imię");

    }

}
