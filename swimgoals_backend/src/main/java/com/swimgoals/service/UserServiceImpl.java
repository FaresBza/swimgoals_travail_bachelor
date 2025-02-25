package com.swimgoals.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.swimgoals.interfaces.UserService;
import com.swimgoals.models.User;
import com.swimgoals.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registUser(User user) throws IllegalArgumentException{
        var existEmailUser = userRepository.existsByEmail(user.getEmail());
        if(existEmailUser) {
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
