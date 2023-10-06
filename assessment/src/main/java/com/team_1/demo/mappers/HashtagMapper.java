package com.team_1.demo.mappers;

import org.mapstruct.Mapper;

import com.team_1.demo.dtos.HashtagDto;
import com.team_1.demo.entities.Hashtag;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HashtagMapper {

    Hashtag hashtagDtoToEntity(HashtagDto hashtagDto);

    List<HashtagDto> entitiesToResponseDtos(List<Hashtag> hashtags);


}
