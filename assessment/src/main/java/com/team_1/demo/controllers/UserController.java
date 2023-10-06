package com.team_1.demo.controllers;

import com.team_1.demo.dtos.*;
import com.team_1.demo.entities.Credentials;
import com.team_1.demo.entities.Tweet;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.team_1.demo.entities.User;
import com.team_1.demo.services.UserService;

import java.util.List;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    // 12 Endpoints
    @GetMapping
    public List<UserResponseDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping
    public UserResponseDto createUser(@RequestBody UserRequestDto userRequestDto) {
        return userService.createUser(userRequestDto);
    }

    @GetMapping("/@{username}")
    public UserResponseDto getUser(@PathVariable String username) {
        return userService.getUser(username);
    }

    @PatchMapping("/@{username}")
    public UserResponseDto updateUsername(@PathVariable String username, @RequestBody UserRequestDto userRequestDto) {
        return userService.updateUsername(username, userRequestDto);
    }

    @DeleteMapping("/@{username}")
    public UserResponseDto deleteUser(@PathVariable String username, @RequestBody CredentialsDto credentials) {
        return userService.deleteUser(username, credentials);
    }

    @GetMapping("/@{username}/following")
    public List<UserResponseDto> getFollowing(@PathVariable String username) {
        return userService.getFollowing(username);
    }

    @GetMapping("/@{username}/followers")
    public List<UserResponseDto> getFollowers(@PathVariable String username) {
        return userService.getFollowers(username);
    }

    @GetMapping("/@{username}/mentions")
    public List<TweetResponseDto> getMentions(@PathVariable String username) {
        return userService.getMentions(username);
    }

    @GetMapping("/@{username}/feed")
    public List<TweetResponseDto> getFeed(@PathVariable String username){
        return userService.getFeed(username);
    }

    @GetMapping("/@{username}/tweets")
    public List<TweetResponseDto> getUserTweets(@PathVariable String username) {
        return userService.getUserTweets(username);
    }

    @PostMapping("/@{username}/follow")
    public void followUser(@PathVariable String username, @RequestBody CredentialsDto credentialsDto) {
        userService.followUser(username, credentialsDto);
    }

    @PostMapping("/@{username}/unfollow")
    public void unfollowUser(@PathVariable String username, @RequestBody CredentialsDto credentialsDto) {
        userService.unfollowUser(username, credentialsDto);
    }
}
