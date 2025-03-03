package com.swimgoals.interfaces;

import com.swimgoals.dto.UserDTO;
import com.swimgoals.models.User;

public interface IUserService {

    public User registUser(UserDTO userDTO);

    public User loginUser(String email, String password);
}
