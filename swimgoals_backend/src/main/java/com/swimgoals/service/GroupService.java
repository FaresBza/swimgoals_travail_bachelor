package com.swimgoals.service;

import java.util.List;

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
            .orElseThrow(() -> new RuntimeException("Nageur non trouvé"));

        Group group = groupRepository.findById(groupId)
            .orElseThrow(() -> new RuntimeException("Groupe non trouvé"));

        user.setGroup(group);
        userRepository.save(user);
    }
}
