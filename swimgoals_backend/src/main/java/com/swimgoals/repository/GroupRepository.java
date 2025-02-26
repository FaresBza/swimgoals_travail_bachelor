package com.swimgoals.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swimgoals.models.Group;

public interface GroupRepository extends JpaRepository<Group, Integer>{

    List<Group> findByCoachId(UUID coach);
}
