package com.swimgoals.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swimgoals.models.Group;

public interface GroupRepository extends JpaRepository<Group, Integer>{

    List<Group> getAllGroupsByCoachId(Integer coachId);
}
