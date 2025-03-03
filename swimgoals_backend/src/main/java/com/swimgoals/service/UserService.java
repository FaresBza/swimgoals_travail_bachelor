package com.swimgoals.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.swimgoals.dto.UserDTO;
import com.swimgoals.interfaces.IUserService;
import com.swimgoals.models.Role;
import com.swimgoals.models.User;
import com.swimgoals.repository.RoleRepository;
import com.swimgoals.repository.UserRepository;

@Service
public class UserService implements IUserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User registUser(UserDTO userDTO) throws IllegalArgumentException {
        var existEmailUser = userRepository.existsByEmail(userDTO.email);
        if (existEmailUser) {
            throw new IllegalArgumentException("Email already exists");
        }

        Role role = roleRepository.findById(userDTO.roleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid role ID"));

        User user = new User();
        user.setFirstname(userDTO.firstname);
        user.setLastname(userDTO.lastname);
        user.setEmail(userDTO.email);
        user.setPassword(userDTO.password);
        user.setRole(role);

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
