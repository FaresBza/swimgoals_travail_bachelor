package com.swimgoals.interfaces;

import java.util.List;

import com.swimgoals.dto.ObjectiveDTO;
import com.swimgoals.models.Objective;

public interface  IObjectiveService {
    
    public List<Objective> getObjectiveBySwimmerId(int swimmerId);

    public Objective creaObjective(ObjectiveDTO objectiveDTO);
}
