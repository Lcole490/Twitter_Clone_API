package com.team_1.demo.services;

import java.util.List;

import com.team_1.demo.dtos.TweetResponseDto;
import com.team_1.demo.entities.Hashtag;

public interface HashtagService {
    List<Hashtag> getAllHashtags();


    List<TweetResponseDto>  getTweetsByHashtag(String label);
}
