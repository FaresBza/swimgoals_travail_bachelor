package com.swimgoals.service;

import org.springframework.stereotype.Service;

import com.swimgoals.dto.GroupDTO;
import com.swimgoals.interfaces.IGroupService;
import com.swimgoals.models.Group;
import com.swimgoals.models.User;
import com.swimgoals.repository.GroupRepository;
import com.swimgoals.repository.UserRepository;

@Service
public class GroupService implements IGroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public GroupService(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Group createGroup(GroupDTO groupDTO) throws IllegalArgumentException {
        Group group = new Group();
        group.setName(groupDTO.getName());

        User coach = userRepository.findById(groupDTO.getCoachId())
                .orElseThrow(() -> new IllegalArgumentException("Coach with the given ID does not exist"));

        group.setCoach(coach);

        return groupRepository.save(group);
    }
}
