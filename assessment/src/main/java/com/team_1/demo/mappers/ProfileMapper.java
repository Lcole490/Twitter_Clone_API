package com.team_1.demo.mappers;

import org.mapstruct.Mapper;

import com.team_1.demo.dtos.ProfileDto;
import com.team_1.demo.entities.Profile;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    Profile profileDtoToEntity(ProfileDto profileDto);
}
