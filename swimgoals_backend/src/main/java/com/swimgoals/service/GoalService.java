package com.swimgoals.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.swimgoals.dto.GoalDTO;
import com.swimgoals.interfaces.IGoalService;
import com.swimgoals.models.Goal;
import com.swimgoals.models.Objective;
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
    public List<Goal> getGoalsByObjectiveId(Integer objectiveId) {
        return goalRepository.findByObjectiveId(objectiveId);
    }

    @Override
    public Goal createGoal(GoalDTO goalDTO) {
        Goal goal = new Goal();

        goal.setTime(goalDTO.getTime());
        goal.setDate(goalDTO.getDate());

        Objective objective = objectiveRepository.findById(goalDTO.getObjectiveId())
                .orElseThrow(() -> new IllegalArgumentException("Objective with given Id does not exist"));

        goal.setObjective(objective);

        return goalRepository.save(goal);
    }
}
