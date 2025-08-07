package com.swimgoals.service;

import java.util.List;
import java.util.Optional;

import com.swimgoals.dto.ObjectiveDTO;
import com.swimgoals.models.Objective;

public interface  ObjectiveService {
    
    /**
     * Retrieves a list of all objectives
     * 
     * @return a list of Objetive objects representing all objectives
     */
    public List<Objective> getAllObjectives();

    /**
     * Retrieves a list of objectives associated with a specific swimmer ID
     * 
     * @param swimmerId The ID of the swimmer whose objectives are to be retrieved
     * @return A list of objectives for the specified swimmer
     */
    public List<Objective> getObjectiveBySwimmerId(int swimmerId);

    /**
     * Creates a new objective based on the provided ObjectiveDTO
     * 
     * @param objectiveDTO The data transfer object containing the details of the objective
     * @return The newly created Objective object
     */
    public Objective createObjective(ObjectiveDTO objectiveDTO);

    /**
     * Retrieves an objective by its unique identifier
     * 
     * @param id The unique identifier of the objective to be retrieved.
     * @return An Optional containing the Objective if found
     */
    public Optional<Objective> getObjectiveById(Integer id);
}
