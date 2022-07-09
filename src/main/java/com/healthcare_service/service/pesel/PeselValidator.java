package com.healthcare_service.service.pesel;

import com.healthcare_service.exceptions.InvalidPeselException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PeselValidator {

    private final int[] weight = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};

    private boolean isNumber(String peselValue) {
        String regex = "[0-9]{11}";

        return peselValue.matches(regex);
    }

    private int getControlNumber(String peselValue) {
        int i = 0;
        int sum = 0;
        String peselWithoutLastDigit = peselValue.substring(0, 10);
        for (char digit : peselWithoutLastDigit.toCharArray()) {
            int temp = Character.getNumericValue(digit) * weight[i];
            if (temp > 9) {
                sum += temp % 10;
            } else {
                sum += temp;
            }
            i++;
        }

        int wynik = 10 - sum % 10;
        if (wynik > 9) return wynik % 10;
        else return wynik;
    }

    private boolean isControlNumberCorrect(String peselValue) {
        return getControlNumber(peselValue) == Character.getNumericValue(peselValue.charAt(10));
    }


    public void validate(String peselValue) {
        if (!(isNumber(peselValue)
                && isControlNumberCorrect(peselValue))) {
            throw new InvalidPeselException();
        }

    }
}
