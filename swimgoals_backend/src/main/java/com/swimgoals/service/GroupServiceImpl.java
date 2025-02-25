package com.swimgoals.service;

import java.util.List;

import com.swimgoals.interfaces.GroupService;
import com.swimgoals.models.Group;
import com.swimgoals.repository.GroupRepository;

public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository){
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Group> getAllGroups() {
        throw new UnsupportedOperationException("Unimplemented method 'getAllGroups'");
    }

    @Override
    public List<Group> getGroupsByCoachId(Integer coachId) {
        throw new UnsupportedOperationException("Unimplemented method 'getGroupsByCoachId'");
    }

    @Override
    public Group createGroupe(Group group) {
        throw new UnsupportedOperationException("Unimplemented method 'createGroupe'");
    }

    @Override
    public Group updateGroup(Integer groupId, Group group) {
        throw new UnsupportedOperationException("Unimplemented method 'updateGroup'");
    }

    @Override
    public void deleteGroup(Integer groupId) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteGroup'");
    }

}
