package com.team_1.demo.controllers;

import com.team_1.demo.services.ValidateService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("validate")
@AllArgsConstructor
public class ValidateController {

    private final ValidateService validateService;

    // 3 Endpoints to implement

    @GetMapping("/username/exists/@{username}")
    public boolean userNameExists(@PathVariable String username) {
        return validateService.userNameExists(username);
    }


}
