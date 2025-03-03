package com.swimgoals.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swimgoals.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
