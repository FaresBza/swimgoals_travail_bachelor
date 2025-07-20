package com.swimgoals.service;

import java.util.Optional;

import com.swimgoals.dto.UserDTO;
import com.swimgoals.models.User;

public interface UserService {

    /**
     * Registers a new user in the database
     * 
     * @param userDTO The data transfer object containing user details
     * @return The newly created User object after successful registration
     */
    public User registUser(UserDTO userDTO);

    /**
     * Authenticates a user based on their email and password
     * 
     * @param email The email address of the user attempting to log in
     * @param password The password associated with the user's account
     * @return The User object if authentication is successful; otherwise, null or an exception may be thrown
     */
    public User loginUser(String email, String password);

    /**
     * Retrieves a user by their unique identifier
     * 
     * @param id The unique identifier of the user to be retrieved
     * @return An Optional containing the User object if found
     */
    public Optional<User> getUserById(Integer id);
}
