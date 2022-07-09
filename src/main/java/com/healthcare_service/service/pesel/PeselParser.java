package com.healthcare_service.service.pesel;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public class PeselParser {
    private final Pesel pesel;


    public PeselData getPeselData() {
        PeselValidator peselValidator = new PeselValidator();
        peselValidator.validate(pesel.getValue());
        return new PeselData(getBirthDayDate(), getGender());
    }

    private int correctMonth(int month) {

        if (month / 10 == 2 || month / 10 == 4 || month / 10 == 6) {
            return month % 10;
        }
        if (month / 10 == 3 || month / 10 == 5 || month / 10 == 7 || month / 10 == 9) {
            return Integer.parseInt("1" + month % 10);
        } else return month;
    }

    private int correctYear(
            int year, int month) {
        String zeroCorrect = "";
        if (String.valueOf(year).length() == 1) {
            zeroCorrect = "0";
        }
        if (month / 10 == 8 || month / 10 == 9) {
            return Integer.parseInt("18" + zeroCorrect + year);
        }
        if (month / 10 == 0 || month / 10 == 1) {
            return Integer.parseInt("19" + zeroCorrect + year);
        }
        if (month / 10 == 2 || month / 10 == 3) {
            return Integer.parseInt("20" + zeroCorrect + year);
        }
        if (month / 10 == 4 || month / 10 == 5) {
            return Integer.parseInt("21" + zeroCorrect + year);
        } else {
            return Integer.parseInt("22" + zeroCorrect + year);
        }
    }

    private int getYear() {
        return Integer.parseInt(pesel.getValue().substring(0, 2));
    }

    private int getMonth() {
        return Integer.parseInt(pesel.getValue().substring(2, 4));
    }

    private LocalDate getBirthDayDate() {
        int day = Integer.parseInt(pesel.getValue().substring(4, 6));
        int month = correctMonth(getMonth());
        int year = correctYear(getYear(), getMonth());

        return LocalDate.of(year, month, day);
    }

    private Gender getGender() {

        if (pesel.getValue().charAt(9) % 2 == 0) {
            return Gender.FEMALE;
        } else return Gender.MALE;
    }
}
