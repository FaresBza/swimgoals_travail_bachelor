package com.swimgoals.interfaces;

import com.swimgoals.models.User;

public interface IUserService {

    public User registUser(User user);

    public User loginUser(String email, String password);
}
