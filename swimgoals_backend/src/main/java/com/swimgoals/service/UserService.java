package com.swimgoals.service;

import com.swimgoals.models.User;
import com.swimgoals.repository.UserRepository;


import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;


    public User registUser(User user) throws Exception{
        if(userRepository.existsByEmail(user.getEmail()) != null) {
            throw new Exception("Email is already exists");
        }
        return userRepository.save(user);
    }

    public User loginUser(String email, String password) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new Exception("User not found");
        }
        if (!user.getPassword().equals(password)){
            throw new Exception("Invalid credentials");
        }
        return user;
    }
}
