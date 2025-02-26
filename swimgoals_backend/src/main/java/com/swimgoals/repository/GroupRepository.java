package com.swimgoals.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.swimgoals.models.Group;

@RepositoryRestResource(exported = false)
@Repository
public interface GroupRepository extends JpaRepository<Group, Integer>{

    List<Group> findByCoachId(UUID coach);
}
