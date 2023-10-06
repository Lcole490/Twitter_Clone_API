package com.team_1.demo.dtos;

import com.team_1.demo.entities.Credentials;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TweetRequestDto {
    private String content;

    private CredentialsDto credentials;
}
