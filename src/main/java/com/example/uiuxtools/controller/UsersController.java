package com.example.uiuxtools.controller;

import com.example.uiuxtools.model.Users;
import com.example.uiuxtools.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Users getUser(@RequestParam Integer userId) {
        return usersService.getUserByUserId(userId);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Users> updateUser(
            @PathVariable Integer userId,
            @RequestBody Users userUpdateRequest) {

        Users updatedUser = usersService.updateUser(userId, userUpdateRequest);
        return ResponseEntity.ok(updatedUser);
    }
}
