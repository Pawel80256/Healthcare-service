package com.healthcare_service.service.pesel;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class PeselData {
    private final LocalDate BirthDate;
    private final Gender gender;
}
