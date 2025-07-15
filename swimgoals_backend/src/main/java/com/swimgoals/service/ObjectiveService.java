package com.swimgoals.service;

import java.util.List;
import java.util.Optional;

import com.swimgoals.dto.ObjectiveDTO;
import com.swimgoals.models.Objective;

public interface  ObjectiveService {
    
    public List<Objective> getObjectiveBySwimmerId(int swimmerId);

    public Objective creaObjective(ObjectiveDTO objectiveDTO);

    public Optional<Objective> getObjectiveById(Integer id);
}
