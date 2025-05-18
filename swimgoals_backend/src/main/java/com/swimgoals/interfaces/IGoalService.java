package com.swimgoals.interfaces;

import com.swimgoals.dto.GoalDTO;
import com.swimgoals.models.Goal;

public interface IGoalService {
    
    public Goal createGoal(GoalDTO goalDTO);
}
