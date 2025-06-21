package com.swimgoals.controller;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swimgoals.dto.UserDTO;
import com.swimgoals.models.Group;
import com.swimgoals.models.User;
import com.swimgoals.repository.UserRepository;
import com.swimgoals.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Operation(summary = "Register a new user", description = "Creates a new user in the database and returns the created user object.", tags = {
            "User Authentication" })
    @ApiResponse(responseCode = "200", description = "User registered successfully", content = @Content(schema = @Schema(implementation = User.class), mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema()))
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserDTO userDTO) {
        try {
            User registeredUser = userService.registUser(userDTO);
            return ResponseEntity.ok(registeredUser);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @Operation(summary = "User login", description = "Authenticates the user and returns the user object if successful.", tags = {
            "User Authentication" })
    @ApiResponse(responseCode = "200", description = "User logged in successfully", content = @Content(schema = @Schema(implementation = User.class), mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Invalid credentials", content = @Content(schema = @Schema()))
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            User loggedUser = userService.loginUser(user.getEmail(), user.getPassword());
            return ResponseEntity.ok(loggedUser);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Utilisateur non trouvé"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Identifiants invalides"));
        }
    }

    @Operation(summary = "Retrieve all groups", description = "Retrieves a list of all swimmers of a group available in the database", tags = {
        "Group" })
    @ApiResponse(responseCode = "200", description = "List of groups retrieved successfully", content = @Content(schema = @Schema(implementation = Group.class), mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Invalid credentials", content = @Content(schema = @Schema()))
    @GetMapping("/groups/{groupId}")
    public List<User> getSwimmersByGroup(@PathVariable Integer groupId) {
        return userRepository.findByGroupIdAndRoleId(groupId, 3); 
    }

    @Operation( summary = "Retrieve a swimmer by ID", description = "Retrieves a swimmer by their unique ID from the database", tags = {
        "User" })
    @ApiResponse(responseCode = "200", description = "List of groups retrieved successfully", content = @Content(schema = @Schema(implementation = Group.class), mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Invalid credentials", content = @Content(schema = @Schema()))
    @GetMapping("/swimmer/{id}")
    public ResponseEntity<Optional<User>> getSwimmerById(@PathVariable Integer id) {
        try {
            Optional<User> swimmer = userService.getUserById(id);
            return ResponseEntity.ok(swimmer);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
