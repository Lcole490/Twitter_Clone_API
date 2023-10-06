package com.team_1.demo.services.impl;

import com.team_1.demo.dtos.*;
import com.team_1.demo.entities.Tweet;
import com.team_1.demo.entities.User;
import com.team_1.demo.exceptions.BadRequestException;
import com.team_1.demo.exceptions.NotAuthorizedException;
import com.team_1.demo.exceptions.NotFoundException;
import com.team_1.demo.mappers.CredentialsMapper;
import com.team_1.demo.mappers.ProfileMapper;
import com.team_1.demo.mappers.TweetMapper;
import com.team_1.demo.repositories.TweetRepository;
import com.team_1.demo.services.ValidateService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import com.team_1.demo.mappers.UserMapper;
import com.team_1.demo.repositories.UserRepository;
import com.team_1.demo.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ProfileMapper profileMapper;
    private final CredentialsMapper credentialsMapper;
    private final TweetRepository tweetRepository;
    private final TweetMapper tweetMapper;
    @Override
    public List<UserResponseDto> getAllUsers() {
        return userMapper.entitiesToResponseDtos(userRepository.findAll());
    }


    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {

        if (userRequestDto.getCredentials() == null || userRequestDto.getProfile() == null || userRequestDto.getCredentials().getUsername() == null || userRequestDto.getCredentials().getPassword() == null || userRequestDto.getProfile().getEmail() == null) {
            throw new BadRequestException("Please provide all required information");
        }
        User checkUser = userRepository.findByUsername(userRequestDto.getCredentials().getUsername());
        if (checkUser != null && !checkUser.isDeleted()) {
            throw new BadRequestException("Username is already active");
        }
        if (checkUser != null && checkUser.isDeleted()) {
            checkUser.setDeleted(false);
            return userMapper.entityToResponseDto(userRepository.saveAndFlush(checkUser));
        }

        User newUser = new User();
        newUser.setProfile(profileMapper.profileDtoToEntity(userRequestDto.getProfile()));
        newUser.setCredentials(credentialsMapper.credentialsDtoToEntity(userRequestDto.getCredentials()));

        return userMapper.entityToResponseDto(userRepository.saveAndFlush(newUser));
    }

    @Override
    public UserResponseDto getUser(String username) {
        User user = userRepository.findByUsername(username);
        validateUser(user);
        return userMapper.entityToResponseDto(user);
    }

    @Override
    public UserResponseDto updateUsername(String username, UserRequestDto userRequestDto) {
        if (userRequestDto.getCredentials() == null || userRequestDto.getProfile() == null) {
            throw new BadRequestException("Please input all required fields");
        }

        User user = userRepository.findByUsername(userRequestDto.getCredentials().getUsername());

        validateUser(user);

        if(!user.getCredentials().equals(credentialsMapper.credentialsDtoToEntity(userRequestDto.getCredentials()))) {
            throw new NotAuthorizedException("Credentials do not match.");
        }

        user.getCredentials().setUsername(username);
        return userMapper.entityToResponseDto(userRepository.saveAndFlush(user));
    }

    @Override
    public UserResponseDto deleteUser(String username, CredentialsDto credentials) {
        User user = userRepository.findByUsername(username);
        validateUser(user);
        if(!user.getCredentials().equals(credentialsMapper.credentialsDtoToEntity(credentials))) {
            throw new NotAuthorizedException("Credentials do not match.");
        }
        UserResponseDto returnUser = userMapper.entityToResponseDto(user);
        user.setDeleted(true);
        userRepository.saveAndFlush(user);
        return returnUser;
    }

    @Override
    public List<UserResponseDto> getFollowing(String username) {
        User user = userRepository.findByUsername(username);
        validateUser(user);

        return userMapper.entitiesToResponseDtos(userRepository.getActiveFollowing(user.getId()));
    }

    @Override
    public List<UserResponseDto> getFollowers(String username) {
        User user = userRepository.findByUsername(username);
        validateUser(user);

        return userMapper.entitiesToResponseDtos(userRepository.getActiveFollowers(user.getId()));
    }
    @Override
    public List<TweetResponseDto> getMentions(String username) {
        User user = userRepository.findByUsername(username);
        validateUser(user);

        return tweetMapper.entitiesToResponseDtos(tweetRepository.getUserMentionedTweets(user.getId()));
    }

    private void validateUser(User user) {
        if (user == null || user.isDeleted()) {
            throw new NotFoundException("User does not exist");
        }
    }

    @Override
    public List<TweetResponseDto> getFeed(String username) {
        Optional<User> userToFind = userRepository.findByCredentialsUsernameAndDeletedFalse(username);
        if (userToFind.isEmpty()) {
            throw new NotFoundException("User Not Found");
        }
        User user = userToFind.get();
        List<Tweet> tweetFeed = new ArrayList<>(user.getTweets());

        for(User follower: user.getFollowing()){
            tweetFeed.addAll(follower.getTweets());
        }
        return tweetMapper.entitiesToResponseDtos(tweetFeed);
    }

    @Override
    public List<TweetResponseDto> getUserTweets(String username) {
        User user = userRepository.findByUsername(username);
        validateUser(user);
        List<Tweet> tweets = new ArrayList<>(user.getTweets());
        for(Tweet t: tweets) {
            if(t.isDeleted()) {
                tweets.remove(t);
            }
        }
        return tweetMapper.entitiesToResponseDtos(tweets);
    }

    @Override
    public void followUser(String username, CredentialsDto credentialsDto){
        User follower = userRepository.findByUsername(credentialsDto.getUsername());
        validateUser(follower);
        if(!follower.getCredentials().equals(credentialsMapper.credentialsDtoToEntity(credentialsDto))) {
            throw new NotAuthorizedException("Credentials do not match.");
        }

        User userToFollow = userRepository.findByUsername(username);

        if(userToFollow == null || userToFollow.isDeleted()){
            throw new NotFoundException("User to follow is not found or is not able to be followed");
        }

        if(follower.getFollowing().contains(userToFollow)){
            throw new BadRequestException("Already following user");
        }

        userToFollow.getFollowers().add(follower);
        userRepository.saveAndFlush(userToFollow);

    }

    @Override
    public void unfollowUser(String username, CredentialsDto credentialsDto){
        Optional<User> followerOptional = userRepository.findByCredentialsUsernameAndDeletedFalse(credentialsDto.getUsername());

        if(followerOptional.isEmpty()){
            throw new NotFoundException("User not found");
        }
        User follower = followerOptional.get();

        if(!follower.getCredentials().equals(credentialsMapper.credentialsDtoToEntity(credentialsDto))) {
            throw new NotAuthorizedException("Credentials do not match.");
        }

        User userToUnfollow = userRepository.findByUsername(username);

        if(userToUnfollow == null || userToUnfollow.isDeleted()){
            throw new NotFoundException("User to unfollow does not exist");
        }

        if(!follower.getFollowing().contains(userToUnfollow)){
            throw new BadRequestException("Not following this user");
        }

        userToUnfollow.getFollowers().remove(follower);
        userRepository.saveAndFlush(userToUnfollow);
    }

}

