package com.swimgoals.service.impl;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.swimgoals.dto.UserDTO;
import com.swimgoals.models.Role;
import com.swimgoals.models.User;
import com.swimgoals.repository.RoleRepository;
import com.swimgoals.repository.UserRepository;
import com.swimgoals.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public User registUser(UserDTO userDTO) throws IllegalArgumentException {
        boolean existEmailUser = userRepository.existsByEmail(userDTO.email);

        if (existEmailUser) {
            throw new IllegalArgumentException("Email already exists");
        }

        Role role = roleRepository.findById(userDTO.roleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid role ID"));
        User user = new User();
        user.setFirstname(userDTO.firstname);
        user.setLastname(userDTO.lastname);
        user.setEmail(userDTO.email);
        user.setPassword(passwordEncoder.encode(userDTO.password));
        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public User loginUser(String email, String hashPassword) throws NoSuchElementException, IllegalArgumentException {
    
        User user = userRepository.findByEmail(email);

        if (user == null){
            throw new NoSuchElementException("User not found");
        }
        if (!passwordEncoder.matches(hashPassword, user.getPassword())){
            throw new IllegalArgumentException("Invalid credentials");
        }
        return user;
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }
}
