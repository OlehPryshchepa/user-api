package com.example.userrestapi.controller;

import com.example.userrestapi.dto.request.UserRequestDto;
import com.example.userrestapi.dto.response.UserResponseDto;
import com.example.userrestapi.mapper.UserMapper;
import com.example.userrestapi.model.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserMapper userMapper;

    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping("/between-dates")
    public List<UserResponseDto> getBetweenDates(
            @RequestParam @Valid @Past @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {

        if (from.isAfter(to)) {
            throw new IllegalArgumentException("“From” should be less than “To”");
        }

        return new ArrayList<>();
    }

    @PostMapping
    public UserResponseDto create(@Valid @RequestBody UserRequestDto userRequestDto) {
        return userMapper.toResponseDto(userMapper.toModel(userRequestDto));
    }

    @PutMapping
    public UserResponseDto update(@RequestParam String email, @RequestBody UserRequestDto updatedUser) {
        return new UserResponseDto();
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto patchUser(@RequestParam String email, @RequestBody Map<String, Object> updates) {
        return new UserResponseDto();
    }


    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String email) {
    }
}
