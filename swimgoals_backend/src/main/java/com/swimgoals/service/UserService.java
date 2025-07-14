package com.swimgoals.service;

import java.util.Optional;

import com.swimgoals.dto.UserDTO;
import com.swimgoals.models.User;

public interface UserService {

    public User registUser(UserDTO userDTO);

    public User loginUser(String email, String password);

    public Optional<User> getUserById(Integer id);
}
