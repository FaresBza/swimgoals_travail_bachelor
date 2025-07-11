package com.swimgoals.interfaces;

import java.util.List;

import com.swimgoals.dto.GoalDTO;
import com.swimgoals.models.Goal;

public interface IGoalService {
    
    public Goal createGoal(GoalDTO goalDTO);

    public List<Goal> getGoalsByObjectiveId(Integer objectiveId);
}
