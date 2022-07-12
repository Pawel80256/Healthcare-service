package com.healthcare_service.service.pesel;

import com.healthcare_service.exceptions.InvalidPeselException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PeselValidatorTest {

    private final PeselValidator peselValidator = new PeselValidator(); //todo: should it be final?

    @ParameterizedTest
    @ValueSource(strings = {"04301493479","70091123748","81120416933","53022442114","47073193938","62062443692","94061673666"})
    void validateCorrectPeselNumbers(String pesel) {
        assertDoesNotThrow(() -> peselValidator.validate(pesel));
    }

    @ParameterizedTest
    @ValueSource(strings = {"test","12345678901","11111111111", "80091123748", "47073193838", "4707319398"})
    void validateNotCorrectPeselNumbers(String pesel){
        assertThrows(InvalidPeselException.class,() -> peselValidator.validate(pesel));
    }
}