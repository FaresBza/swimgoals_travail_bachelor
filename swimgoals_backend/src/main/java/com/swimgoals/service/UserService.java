package com.swimgoals.service;

import com.swimgoals.dto.UserDTO;
import com.swimgoals.models.User;

public interface UserService {

    public User registUser(UserDTO userDTO);

    public User loginUser(String email, String password);
}
