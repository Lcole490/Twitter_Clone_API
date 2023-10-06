package com.team_1.demo.mappers;


import org.mapstruct.Mapper;

import com.team_1.demo.dtos.UserRequestDto;
import com.team_1.demo.dtos.UserResponseDto;
import com.team_1.demo.entities.User;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProfileMapper.class, CredentialsMapper.class})
public interface UserMapper {

    @Mapping(target = "username", source = "credentials.username")
    UserResponseDto entityToResponseDto(User user);

    @Mapping(target = "username", source = "credentials.username")
    List<UserResponseDto> entitiesToResponseDtos(List<User> users);
}
