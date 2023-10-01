package com.example.userrestapi.dto.request;

import com.example.userrestapi.lib.Adult;
import com.example.userrestapi.util.Patterns;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRequestDto {
    @Email(regexp = Patterns.EMAIL, message = "Your email have to match with pattern: " + Patterns.EMAIL)
    private String email;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Past
    @Adult
    @NotNull(message = "birth date can`t be null")
    private LocalDate birthDate;
    private String address;
    @Pattern(regexp = Patterns.PHONE_NUMBER)
    private String phoneNumber;
}
