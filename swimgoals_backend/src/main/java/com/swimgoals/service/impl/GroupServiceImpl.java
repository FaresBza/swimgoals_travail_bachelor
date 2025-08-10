package com.swimgoals.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.swimgoals.dto.GroupDTO;
import com.swimgoals.models.Group;
import com.swimgoals.models.User;
import com.swimgoals.repository.GroupRepository;
import com.swimgoals.repository.UserRepository;
import com.swimgoals.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public GroupServiceImpl(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
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

    @Override
    public List<Group> getGroupsByCoachId(int coachId) {
        return groupRepository.findByCoachId(coachId);
    }

    @Override
    public void joinGroup(int swimmerId, int groupId) {
        User user = userRepository.findById(swimmerId)
            .orElseThrow(() -> new RuntimeException("Swimmer not found"));

        Group group = groupRepository.findById(groupId)
            .orElseThrow(() -> new RuntimeException("Group not found"));

        user.setGroup(group);
        userRepository.save(user);
    }

    @Override
    public Optional<Group> getGroupById(Integer id) {
        return groupRepository.findById(id);
    }
}
