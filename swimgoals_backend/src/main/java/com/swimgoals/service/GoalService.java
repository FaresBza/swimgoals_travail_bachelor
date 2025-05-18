package com.swimgoals.service;

import org.springframework.stereotype.Service;

import com.swimgoals.dto.GoalDTO;
import com.swimgoals.interfaces.IGoalService;
import com.swimgoals.models.Goal;
import com.swimgoals.repository.GoalRepository;
import com.swimgoals.repository.ObjectiveRepository;

@Service
public class GoalService implements IGoalService {
    
    private final GoalRepository goalRepository;
    private final ObjectiveRepository objectiveRepository;

    public GoalService(GoalRepository goalRepository, ObjectiveRepository objectiveRepository) {
        this.goalRepository = goalRepository;
        this.objectiveRepository = objectiveRepository;
    }

    @Override
    public Goal createGoal(GoalDTO goalDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createGoal'");
    }
}
