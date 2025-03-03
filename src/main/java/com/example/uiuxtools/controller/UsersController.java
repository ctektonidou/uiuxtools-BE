package com.example.uiuxtools.controller;

import com.example.uiuxtools.model.Users;
import com.example.uiuxtools.service.UsersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UsersController {
    private final UsersService usersService;

    // Constructor-based Dependency Injection
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    // Get user by userId
    @GetMapping
    public List<Users> getUser(@RequestBody Integer userId) {
        return usersService.searchUserByUserId(userId);
    }
}
