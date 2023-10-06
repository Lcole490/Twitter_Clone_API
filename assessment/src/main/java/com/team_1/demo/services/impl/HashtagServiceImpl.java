package com.team_1.demo.services.impl;

import com.team_1.demo.dtos.TweetResponseDto;
import com.team_1.demo.entities.Tweet;
import com.team_1.demo.exceptions.NotFoundException;
import com.team_1.demo.mappers.HashtagMapper;
import com.team_1.demo.mappers.TweetMapper;
import com.team_1.demo.repositories.TweetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.team_1.demo.entities.Hashtag;
import com.team_1.demo.repositories.HashtagRepository;
import com.team_1.demo.services.HashtagService;

import java.util.List;

@Service
@AllArgsConstructor
public class HashtagServiceImpl implements HashtagService {

    private final HashtagRepository hashtagRepository;
    private final HashtagMapper hashtagMapper;

    private final TweetRepository tweetRepository;
    private final TweetMapper tweetMapper;
    @Override
    public List<Hashtag> getAllHashtags(){
        return hashtagRepository.findAll();
    }


    @Override
    public List<TweetResponseDto> getTweetsByHashtag(String label){
        Hashtag hashtag = hashtagRepository.findByLabel(label);
        if (hashtag == null){
            throw new NotFoundException("No Hashtag Found with given label");
        }

        List<Tweet> taggedTweets = tweetRepository.findByContentContainingAndDeletedFalse("#" + label);
        return tweetMapper.entitiesToResponseDtos(taggedTweets);
    }
}
