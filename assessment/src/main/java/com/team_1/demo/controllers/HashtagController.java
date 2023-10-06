package com.team_1.demo.controllers;

import com.team_1.demo.dtos.TweetResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team_1.demo.entities.Hashtag;
import com.team_1.demo.services.HashtagService;

import java.util.List;

@RestController
@RequestMapping("tags")
@AllArgsConstructor
public class HashtagController {

    private HashtagService hashtagService;

    // 2 Endpoints

    @GetMapping
    public List<Hashtag> getAllHashtags(){
        return hashtagService.getAllHashtags();
    }


    @GetMapping("/{label}")
    public List<TweetResponseDto> getTweetsByHashtag(@PathVariable String label){
        return hashtagService.getTweetsByHashtag(label);
    }
}
