package com.swimgoals.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.swimgoals.dto.ObjectiveDTO;
import com.swimgoals.models.Objective;
import com.swimgoals.models.Swim;
import com.swimgoals.models.User;
import com.swimgoals.repository.ObjectiveRepository;
import com.swimgoals.repository.SwimRepository;
import com.swimgoals.repository.UserRepository;
import com.swimgoals.service.ObjectiveService;

@Service
public class ObjectiveServiceImpl implements ObjectiveService {
    
    private final ObjectiveRepository objectiveRepository;
    private final SwimRepository swimRepository;
    private final UserRepository userRepository;

    public ObjectiveServiceImpl(ObjectiveRepository objectiveRepository, SwimRepository swimRepository, UserRepository userRepository) {
        this.objectiveRepository = objectiveRepository;
        this.swimRepository = swimRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Objective> getAllObjectives() {
        return objectiveRepository.findAll();
    }

    @Override
    public List<Objective> getObjectiveBySwimmerId(int swimmerId) {
        return objectiveRepository.findBySwimmerId(swimmerId);
    }

    @Override
    public Objective createObjective(ObjectiveDTO objectiveDTO) {
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

    @Override
    public Optional<Objective> getObjectiveById(Integer id) {
        return objectiveRepository.findById(id);
    }
}
