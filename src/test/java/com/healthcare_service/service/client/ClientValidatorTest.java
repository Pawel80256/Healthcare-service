package com.healthcare_service.service.client;

import com.healthcare_service.DTO.address.AddressInputDTO;
import com.healthcare_service.DTO.client.ClientInputDTO;
import com.healthcare_service.exceptions.InvalidRegisterData;
import com.healthcare_service.exceptions.TakenResourceException;
import com.healthcare_service.repository.ClientRepository;
import com.healthcare_service.repository.DoctorRepository;
import com.healthcare_service.service.emailAddress.EmailAddress;
import com.healthcare_service.service.emailAddress.MyEmailValidator;
import com.healthcare_service.service.pesel.PeselValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientValidatorTest {
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private DoctorRepository doctorRepository;
    @Mock
    private PeselValidator peselValidator;
    @Mock
    private MyEmailValidator myEmailValidator;

    private ClientValidator clientValidator;

    private ClientInputDTO clientInput;

    @BeforeEach
    void setUp() {
        clientValidator = new ClientValidator(clientRepository,
                doctorRepository,
                peselValidator,
                myEmailValidator);

        clientInput = ClientInputDTO.builder()
                .firstName("John")
                .lastName("Cena")
                .username("username")
                .password("password")
                .emailAddress("jcena@gmail.com")
                .pesel("04301493479")
                .addressInputDTO(
                        AddressInputDTO.builder()
                                .city("Warszawa")
                                .roadName("Kopernika")
                                .buildingNumber("13")
                                .localNumber("40")
                                .build()

                ).build();
    }


    @Test
    void validateCorrectInput() {
        when(clientRepository.existsByUsername(clientInput.getUsername())).thenReturn(false);
        when(doctorRepository.existsByUsername(clientInput.getUsername())).thenReturn(false);
        when(clientRepository.existsByEmailAddress(clientInput.getEmailAddress())).thenReturn(false);
        when(doctorRepository.existsByEmailAddress(clientInput.getEmailAddress())).thenReturn(false);
        when(clientRepository.existsByPesel(clientInput.getPesel())).thenReturn(false);

        assertDoesNotThrow(() -> clientValidator.validateInput(clientInput));
        verify(peselValidator).validate(clientInput.getPesel());
        verify(myEmailValidator).validate(new EmailAddress(clientInput.getEmailAddress()));

    }

    @Test
    void validateTooShortUsername(){
        clientInput.setUsername("user");

        assertThrows(InvalidRegisterData.class, () -> clientValidator.validateInput(clientInput));
    }

    @Test
    void validateTooShortPassword(){
        clientInput.setPassword("123");

        assertThrows(InvalidRegisterData.class, () -> clientValidator.validateInput(clientInput));
    }

    @Test
    void validateBlankFirstName(){
        clientInput.setFirstName("  ");

        assertThrows(InvalidRegisterData.class, () -> clientValidator.validateInput(clientInput));
    }

    @Test
    void validateTakenUserName(){
        when(clientRepository.existsByUsername(clientInput.getUsername())).thenReturn(true);
        assertThrows(TakenResourceException.class,() -> clientValidator.validateInput(clientInput));

        when(clientRepository.existsByUsername(clientInput.getUsername())).thenReturn(false);
        when(doctorRepository.existsByEmailAddress(clientInput.getEmailAddress())).thenReturn(true);
        assertThrows(TakenResourceException.class,() -> clientValidator.validateInput(clientInput));
    }

    @Test
    void validateTakenEmailAddress(){
        when(clientRepository.existsByEmailAddress(clientInput.getEmailAddress())).thenReturn(true);
        assertThrows(TakenResourceException.class,() -> clientValidator.validateInput(clientInput));

        when(clientRepository.existsByEmailAddress(clientInput.getEmailAddress())).thenReturn(false);
        when(doctorRepository.existsByEmailAddress(clientInput.getEmailAddress())).thenReturn(true);
        assertThrows(TakenResourceException.class,() -> clientValidator.validateInput(clientInput));
    }

    @Test
    void validateTakenPeselNumber(){
        when(clientRepository.existsByPesel(clientInput.getPesel())).thenReturn(true);
        assertThrows(TakenResourceException.class, () -> clientValidator.validateInput(clientInput));
    }
}