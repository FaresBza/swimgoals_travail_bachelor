package com.swimgoals.service;

import java.util.List;

import com.swimgoals.dto.GoalDTO;
import com.swimgoals.models.Goal;

public interface GoalService {
    
    /**
     * Creates a new goal based on the provided GoalDTO object
     * 
     * @param goalDTO The data transfer object containing goal details
     * @return The created Goal object
     */
    public Goal createGoal(GoalDTO goalDTO);

    /**
     * Retrieves a list of goals associated with the specified objective ID
     * 
     * @param objectiveId The ID of the objective for which goals are to be fetched
     * @return A list of Goal objects linked to the given objective ID
     */
    public List<Goal> getGoalsByObjectiveId(Integer objectiveId);
}
