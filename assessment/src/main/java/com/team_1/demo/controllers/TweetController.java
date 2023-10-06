package com.team_1.demo.controllers;

import com.team_1.demo.dtos.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.team_1.demo.entities.Tweet;
import com.team_1.demo.services.TweetService;

import java.util.List;

@RestController
@RequestMapping("tweets")
@AllArgsConstructor
public class TweetController {

    private TweetService tweetService;

    // 13 Endpoints

    @GetMapping
    public List<TweetResponseDto> getAllTweets(){ return tweetService.getAllTweets();}

    @GetMapping("/{id}")
    public TweetResponseDto getTweet(@PathVariable Long id) {
        return tweetService.getTweet(id);
    }

    @PostMapping
    public TweetResponseDto createTweet(@RequestBody TweetRequestDto tweetRequestDto) {
        return tweetService.createTweet(tweetRequestDto);
    }

    @DeleteMapping("/{id}")
    public TweetResponseDto deleteTweet(@PathVariable Long id, @RequestBody CredentialsDto credentialsDto) {
        return tweetService.deleteTweet(id, credentialsDto);
    }

    @PostMapping("/{id}/like")
    public void likedTweet(@PathVariable Long id, @RequestBody CredentialsDto credentialsDto) {
        tweetService.likedTweet(id, credentialsDto);
    }


    @GetMapping("/{id}/context")
    public ContextDto getContext(@PathVariable Long id) {
        return tweetService.getContext(id);
    }

    @PostMapping("/{id}/repost")
    public TweetResponseDto repostTweet(@PathVariable Long id, @RequestBody CredentialsDto credentials) {
        return tweetService.repostTweet(id, credentials);
    }

    @GetMapping("/{id}/likes")
    public List<UserResponseDto> getTweetLikes(@PathVariable Long id) {
        return tweetService.getTweetLikes(id);
    }

    @GetMapping("{id}/replies")
    public List<TweetResponseDto> getReplies(@PathVariable Long id) {
        return tweetService.getReplies(id);
    }

    @GetMapping("{id}/tags")
    public List<HashtagDto> getTags(@PathVariable Long id) {
        return tweetService.getTags(id);
    }

    @PostMapping("/{id}/reply")
    public TweetResponseDto createReplyToTweet(@PathVariable Long id, @RequestBody TweetRequestDto tweetRequestDto){
            return tweetService.createReplyToTweet(id, tweetRequestDto);
    }

    @GetMapping("/{id}/reposts")
    public List<TweetResponseDto> getTweetReposts(@PathVariable long id){
        return tweetService.getTweetReposts(id);
    }

    @GetMapping("/{id}/mentions")
    public List<UserResponseDto>  getTweetMentions(@PathVariable Long id){
        return tweetService.getTweetMentions(id);

    }
}
