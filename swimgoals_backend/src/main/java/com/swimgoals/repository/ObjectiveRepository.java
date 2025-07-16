package com.swimgoals.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.swimgoals.models.Objective;

@RepositoryRestResource(exported = false)
@Repository
public interface  ObjectiveRepository extends JpaRepository<Objective, Integer> {
    
    List<Objective> findBySwimmerId(int swimmerId);
}
