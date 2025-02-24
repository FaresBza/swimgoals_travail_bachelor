package com.swimgoals.service;

import com.swimgoals.models.User;
import com.swimgoals.repository.UserRepository;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.swimgoals.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registUser(User user) throws IllegalArgumentException{
        if(userRepository.existsByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("Email is already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public User loginUser(String email, String password) throws NoSuchElementException, IllegalArgumentException {
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new NoSuchElementException("User not found");
        }
        if (!user.getPassword().equals(password)){
            throw new IllegalArgumentException("Invalid credentials");
        }
        return user;
    }
}
