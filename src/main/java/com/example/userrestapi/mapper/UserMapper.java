package com.example.userrestapi.mapper;

import com.example.userrestapi.dto.request.UserRequestDto;
import com.example.userrestapi.dto.response.UserResponseDto;
import com.example.userrestapi.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toModel(UserRequestDto userRequestDto);
    UserResponseDto toResponseDto(User model);
}

