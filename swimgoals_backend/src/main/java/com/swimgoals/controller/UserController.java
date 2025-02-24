package com.swimgoals.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.swimgoals.models.User;
import com.swimgoals.service.UserServiceImpl;

@RestController
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    public ResponseEntity<User> registerUser(@RequestBody User user) {
        try {
            User registeredUser = userServiceImpl.registUser(user);
            return ResponseEntity.ok(registeredUser);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<User> loginUser(String email, String password) {
        try {
            User loggedUser = userServiceImpl.loginUser(email, password);
            return ResponseEntity.ok(loggedUser);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
