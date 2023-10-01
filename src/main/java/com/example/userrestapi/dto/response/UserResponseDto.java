package com.example.userrestapi.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserResponseDto {
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String address;
    private String phoneNumber;
}
