package com.swimgoals.service;

import com.swimgoals.models.User;
import com.swimgoals.repository.UserRepository;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    /**
     * 
     * @param user
     * @return L'utilisateur enregistré si l'email est unique
     * @throws IllegalArgumentException
     */
    public User registUser(User user) throws IllegalArgumentException{
        if(userRepository.existsByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("Email is already exists");
        }
        return userRepository.save(user);
    }

    /**
     * 
     * @param email
     * @param password
     * @return L'utilisateur si les informations d'identification sont valides
     * @throws NoSuchElementException
     * @throws IllegalArgumentException
     */
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
