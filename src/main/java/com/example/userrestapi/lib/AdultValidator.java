package com.example.userrestapi.lib;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

public class AdultValidator implements ConstraintValidator<Adult, LocalDate> {
    @Value("${required.year}")
    private int age;

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Разрешено, если дата рождения не указана
        }

        LocalDate today = LocalDate.now();
        LocalDate minDate = today.minusYears(age);

        return !value.isAfter(minDate);
    }
}
