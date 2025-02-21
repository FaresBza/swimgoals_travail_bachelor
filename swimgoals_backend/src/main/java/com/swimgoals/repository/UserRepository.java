package com.swimgoals.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swimgoals.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    
}
