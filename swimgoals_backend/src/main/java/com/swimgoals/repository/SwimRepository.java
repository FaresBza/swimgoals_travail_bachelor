package com.swimgoals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.swimgoals.models.Swim;

@Repository
@RepositoryRestResource(exported = false)
public interface  SwimRepository extends JpaRepository<Swim, Integer>{
    
}
