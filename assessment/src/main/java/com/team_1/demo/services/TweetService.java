package com.team_1.demo.services;

import java.util.List;
import com.team_1.demo.dtos.*;

public interface TweetService {

    List<TweetResponseDto> getAllTweets();

    TweetResponseDto getTweet(Long id);

    TweetResponseDto createTweet(TweetRequestDto tweetRequestDto);

    TweetResponseDto deleteTweet(Long id, CredentialsDto credentialsDto);

    void likedTweet(Long id, CredentialsDto credentialsDto);

    ContextDto getContext(Long id);

    TweetResponseDto repostTweet(Long id, CredentialsDto credentials);

    List<UserResponseDto> getTweetLikes(Long id);

    List<TweetResponseDto> getReplies(Long id);

    List<HashtagDto> getTags(Long id);

    TweetResponseDto createReplyToTweet(Long id, TweetRequestDto tweetRequestDto);

    List<TweetResponseDto> getTweetReposts(Long id);

    List<UserResponseDto>  getTweetMentions(Long id);
}
