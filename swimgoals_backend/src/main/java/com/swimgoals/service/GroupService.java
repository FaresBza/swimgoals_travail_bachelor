package com.swimgoals.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.swimgoals.interfaces.IGroupService;
import com.swimgoals.models.Group;
import com.swimgoals.repository.GroupRepository;

@Service
public class GroupService implements IGroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public List<Group> getAllGroupsByCoachId(UUID coachId) {
        return groupRepository.findByCoachId(coachId);
    }


    @Override
    public Group createGroup(Group group) {
        if (group.getName() == null || group.getName().isEmpty()) {
            throw new IllegalArgumentException("Le nom du groupe ne peut pas être vide.");
        }
        return groupRepository.save(group);
    }

    @Override
    public Group updateGroup(Integer groupId, Group group) {
        if (group.getName() == null || group.getName().isEmpty()) {
            throw new IllegalArgumentException("Le nom du groupe ne peut pas être vide.");
        }

        return groupRepository.findById(groupId)
                .map(existingGroup -> {
                    existingGroup.setName(group.getName());
                    existingGroup.setCoach(group.getCoach());
                    return groupRepository.save(existingGroup);
                })
                .orElseThrow(() -> new IllegalArgumentException("Groupe non trouvé avec l'ID: " + groupId));
    }

    @Override
    public void deleteGroup(Integer groupId) {
        if (!groupRepository.existsById(groupId)) {
            throw new IllegalArgumentException("Le groupe d'ID " + groupId + " est introuvable.");
        }
        groupRepository.deleteById(groupId);
    }
}
