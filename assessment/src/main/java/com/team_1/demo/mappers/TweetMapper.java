package com.team_1.demo.mappers;

import org.mapstruct.Mapper;

import com.team_1.demo.dtos.TweetRequestDto;
import com.team_1.demo.dtos.TweetResponseDto;
import com.team_1.demo.entities.Tweet;

import java.util.List;

@Mapper(componentModel = "spring", uses= {UserMapper.class})
public interface TweetMapper {

    Tweet requestDtoToEntity(TweetRequestDto tweetRequestDto);

    TweetResponseDto entityToResponseDto(Tweet tweet);

    List<TweetResponseDto> entitiesToResponseDtos(List<Tweet> tweets);
}
