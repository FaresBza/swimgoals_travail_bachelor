package com.swimgoals.interfaces;

import java.util.List;

import com.swimgoals.models.Group;

public interface IGroupService {

    List<Group> getAllGroups();

    List<Group> getAllGroupsByCoachId(String coachId);

    Group createGroup(Group group);

    Group updateGroup(Integer groupId, Group group);

    void deleteGroup(Integer groupId);
}
