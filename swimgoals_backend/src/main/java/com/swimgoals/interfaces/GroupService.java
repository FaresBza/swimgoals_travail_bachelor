package com.swimgoals.interfaces;

import java.util.List;

import com.swimgoals.models.Group;

public interface GroupService {

    List<Group> getAllGroups();

    List<Group> getGroupsByCoachId(Integer coachId);

    Group createGroup(Group group);

    Group updateGroup(Integer groupId, Group group);

    void deleteGroup(Integer groupId);
}
