package com.swimgoals.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.swimgoals.models.Objective;
import com.swimgoals.repository.ObjectiveRepository;

@Service
public class ObjectiveService {
    
    private final ObjectiveRepository objectiveRepository;

    public ObjectiveService(ObjectiveRepository objectiveRepository) {
        this.objectiveRepository = objectiveRepository;
    }

    public List<Objective> getObjectiveBySwimmerId(int swimmerId) {
        return objectiveRepository.findBySwimmerId(swimmerId);
    }
}
