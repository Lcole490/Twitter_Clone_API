package com.team_1.demo.mappers;

import org.mapstruct.Mapper;
import com.team_1.demo.dtos.CredentialsDto;
import com.team_1.demo.entities.Credentials;

@Mapper(componentModel = "spring")
public interface CredentialsMapper {

    Credentials credentialsDtoToEntity(CredentialsDto credentialsDto);

    Credentials requestDtoToEntity(CredentialsDto credentialsDto);

}
