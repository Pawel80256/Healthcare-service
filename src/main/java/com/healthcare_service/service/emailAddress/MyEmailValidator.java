package com.healthcare_service.service.emailAddress;

import com.healthcare_service.exceptions.InvalidEmailException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyEmailValidator {

    public void validate(EmailAddress emailAddress) {
        if (!EmailValidator.getInstance().isValid(emailAddress.getValue()))
            throw new InvalidEmailException();
    }
}
