package com.swimgoals.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.swimgoals.dto.ObjectiveDTO;
import com.swimgoals.interfaces.IObjectiveService;
import com.swimgoals.models.Objective;
import com.swimgoals.models.Swim;
import com.swimgoals.models.User;
import com.swimgoals.repository.ObjectiveRepository;
import com.swimgoals.repository.SwimRepository;
import com.swimgoals.repository.UserRepository;

@Service
public class ObjectiveService implements IObjectiveService {
    
    private final ObjectiveRepository objectiveRepository;
    private final SwimRepository swimRepository;
    private final UserRepository userRepository;

    public ObjectiveService(ObjectiveRepository objectiveRepository, SwimRepository swimRepository, UserRepository userRepository) {
        this.objectiveRepository = objectiveRepository;
        this.swimRepository = swimRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Objective> getObjectiveBySwimmerId(int swimmerId) {
        return objectiveRepository.findBySwimmerId(swimmerId);
    }

    @Override
    public Objective creaObjective(ObjectiveDTO objectiveDTO) {
        Objective objective = new Objective();

        objective.setDistance(objectiveDTO.getDistance());
        objective.setTime(objectiveDTO.getTime());

        Swim swim = swimRepository.findById(objectiveDTO.getSwimId())
            .orElseThrow(() -> new IllegalArgumentException("Invalid swim ID"));
        
        User swimmer = userRepository.findById(objectiveDTO.getSwimmerId())
                .orElseThrow(() -> new IllegalArgumentException("Swimmer with the given ID does not exist"));
        
        objective.setSwim(swim);
        objective.setSwimmer(swimmer);
        
        return objectiveRepository.save(objective);
    }

}
