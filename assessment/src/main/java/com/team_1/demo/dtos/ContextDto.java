package com.team_1.demo.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.team_1.demo.entities.Tweet;

@NoArgsConstructor
@Data
public class ContextDto {

    private Tweet target;

    private List<TweetResponseDto> before;

    private List<TweetResponseDto> after;
}
