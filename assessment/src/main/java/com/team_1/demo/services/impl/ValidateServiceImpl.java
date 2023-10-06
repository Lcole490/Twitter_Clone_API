package com.team_1.demo.services.impl;

import com.team_1.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.team_1.demo.services.ValidateService;
@Service
@RequiredArgsConstructor
public class ValidateServiceImpl implements ValidateService {

    private final UserRepository userRepository;
    @Override
    public boolean userNameExists(String username) {
        return userRepository.findAll().contains(userRepository.findByUsername(username));
    }
}
