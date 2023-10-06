package com.team_1.demo.services;

import java.util.List;

import com.team_1.demo.dtos.*;
import com.team_1.demo.entities.Tweet;

public interface UserService {
    List<UserResponseDto> getAllUsers();

    UserResponseDto createUser(UserRequestDto userRequestDto);

    UserResponseDto getUser(String username);

    UserResponseDto updateUsername(String username, UserRequestDto userRequestDto);

    UserResponseDto deleteUser(String username, CredentialsDto credentials);

    List<UserResponseDto> getFollowing(String username);

    List<UserResponseDto> getFollowers(String username);

    List<TweetResponseDto> getMentions(String username);

    List<TweetResponseDto> getFeed(String username);

    List<TweetResponseDto> getUserTweets(String username);

    void followUser(String username, CredentialsDto credentialsDto);

    void unfollowUser(String username, CredentialsDto credentialsDto);
}
