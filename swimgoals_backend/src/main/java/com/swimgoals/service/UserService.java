package com.swimgoals.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.swimgoals.interfaces.IUserService;
import com.swimgoals.models.User;
import com.swimgoals.repository.UserRepository;

@Service
public class UserService implements IUserService{

    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registUser(User user) throws IllegalArgumentException{
        var existEmailUser = userRepository.existsByEmail(user.getEmail());
        if(existEmailUser == null) {
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
