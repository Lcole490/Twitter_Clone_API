package com.team_1.demo.dtos;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team_1.demo.entities.Tweet;
import com.team_1.demo.entities.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TweetResponseDto {

    private Long id;

    private UserResponseDto author;

    private Timestamp posted;

    private String content;

    private TweetResponseDto inReplyTo;

    private TweetResponseDto repostOf;
}
