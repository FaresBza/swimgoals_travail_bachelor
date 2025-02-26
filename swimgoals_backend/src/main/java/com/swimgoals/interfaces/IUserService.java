package com.swimgoals.interfaces;

import java.util.NoSuchElementException;

import com.swimgoals.models.User;

public interface IUserService {

    /**
     * 
     * @param user
     * @return L'utilisateur enregistré si l'email est unique
     * @throws IllegalArgumentException
     */
    public User registUser(User user);

    /**
     * 
     * @param email
     * @param password
     * @return L'utilisateur si les informations d'identification sont valides
     * @throws NoSuchElementException
     * @throws IllegalArgumentException
     */
    public User loginUser(String email, String password);
}
